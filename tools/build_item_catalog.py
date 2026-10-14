#!/usr/bin/env python3
"""Generate the self-contained local item catalogue page."""

import base64
import json
import mimetypes
import re
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/super_solar_panels_rioutouscraft"
TEXTURES = ASSETS / "textures"
ITEM_MODELS = ASSETS / "models/item"
BLOCK_MODELS = ASSETS / "models/block"
LANG = ASSETS / "lang"
OUT = ROOT / "item-catalog.html"
MODID = "super_solar_panels_rioutouscraft"


def read_lang(path):
    data = {}
    if not path.exists():
        return data
    for line in path.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if not line or line.startswith("#") or "=" not in line:
            continue
        key, value = line.split("=", 1)
        data[key.strip()] = re.sub(r"§[0-9a-fk-or]", "", value).strip()
    return data


ZH = read_lang(LANG / "zh_CN.lang")
EN = read_lang(LANG / "en_US.lang")


def tr(*keys, fallback=""):
    for key in keys:
        if key in ZH:
            return ZH[key]
        if key in EN:
            return EN[key]
    return fallback


TEXTURE_LOOKUP = {
    str(path.relative_to(TEXTURES)).lower(): path
    for path in TEXTURES.rglob("*.png")
}


def texture_ref(reference):
    if not reference:
        return None
    reference = reference.split(":", 1)[-1].lstrip("#")
    if reference.startswith("textures/"):
        reference = reference[9:]
    key = reference.lower()
    if not key.endswith(".png"):
        key += ".png"
    return TEXTURE_LOOKUP.get(key)


def image_data(path):
    if not path or not path.exists():
        return ""
    mime = mimetypes.guess_type(path.name)[0] or "image/png"
    return "data:" + mime + ";base64," + base64.b64encode(path.read_bytes()).decode("ascii")


def json_file(path):
    try:
        return json.loads(path.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError):
        return {}


def model_texture(path, block=False):
    data = json_file(path)
    textures = data.get("textures", {})
    for key in ["layer0", "up", "top", "all", "particle", "side", "north", "down"] + list(textures):
        found = texture_ref(textures.get(key))
        if found:
            return found
    if block:
        for candidate in ["blocks/" + path.stem, "blocks/" + path.stem + "_top", "blocks/" + path.stem + "_still"]:
            found = texture_ref(candidate)
            if found:
                return found
    return None


def title(value):
    return re.sub(r"[_-]+", " ", value).strip().title()


ITEM_NAME_MAP = {
    "spectral_solar_helmet": ("super_solar_panels_rioutouscraft.solar_helmets.spectral", "光谱太阳头盔"),
    "singular_solar_helmet": ("super_solar_panels_rioutouscraft.solar_helmets.singular", "奇异太阳头盔"),
    "advanced_solar_helmet": ("super_solar_panels_rioutouscraft.solar_helmets.advanced", "高级太阳能头盔"),
    "hybrid_solar_helmet": ("super_solar_panels_rioutouscraft.solar_helmets.hybrid", "混合太阳能头盔"),
    "ultimate_solar_helmet": ("super_solar_panels_rioutouscraft.solar_helmets.ultimate", "终极混合太阳能头盔"),
    "helmet_solar_helmet": ("super_solar_panels_rioutouscraft.solar_helmets.helmet", "高级量子头盔"),
    "gravi_chestplate": ("super_solar_panels_rioutouscraft.graviChestplate1", "进阶量子胸甲"),
    "gravi_leggins": ("super_solar_panels_rioutouscraft.graviLeggins", "进阶量子护腿"),
    "gravi_boosts": ("super_solar_panels_rioutouscraft.graviBoosts", "进阶量子靴"),
    "spectral_battery": ("super_solar_panels_rioutouscraft.spectral_battery", "量子能量水晶"),
    "quantumsaber": ("super_solar_panels_rioutouscraft.quantumsaber", "量子剑"),
    "spectralsaber": ("super_solar_panels_rioutouscraft.spectralsaber", "光谱剑"),
    "twelve_heat_storage": ("super_solar_panels_rioutouscraft.twelve_heat_storage", "120k冷却单元"),
    "max_heat_storage": ("super_solar_panels_rioutouscraft.max_heat_storage", "240k冷却单元"),
    "proton_fuel_rod": ("super_solar_panels_rioutouscraft.proton_fuel_rod", "燃料棒（质子）"),
    "dual_proton_fuel_rod": ("super_solar_panels_rioutouscraft.dual_proton_fuel_rod", "双联燃料棒（质子）"),
    "quad_proton_fuel_rod": ("super_solar_panels_rioutouscraft.quad_proton_fuel_rod", "四联燃料棒（质子）"),
    "eit_proton_fuel_rod": ("super_solar_panels_rioutouscraft.eit_proton_fuel_rod", "八联燃料棒（质子）"),
    "rotor_carbon1": ("super_solar_panels_rioutouscraft.rotor_carbon1", "铱转子"),
    "rotor_carbon2": ("super_solar_panels_rioutouscraft.rotor_carbon2", "高级铱转子"),
    "rotor_carbon3": ("super_solar_panels_rioutouscraft.rotor_carbon3", "光谱转子"),
    "item_ult_drill": ("super_solar_panels_rioutouscraft.ItemUltDrill", "量子钻头"),
}


def item_name(rel):
    stem = Path(rel).stem
    if rel.startswith("crafting/"):
        return tr("super_solar_panels_rioutouscraft.crafting." + stem, fallback=title(stem))
    if rel.startswith("solar_panel_glass/"):
        return tr("item." + MODID + ".solar_panel_glass." + stem + ".name", fallback=title(stem) + "玻璃")
    if rel.startswith("solar_panel_battery/"):
        return tr("item." + MODID + ".solar_panel_battery." + stem + ".name", fallback=title(stem) + "储能模块")
    if rel.startswith("solar_panel_output/"):
        return tr("item." + MODID + ".solar_panel_output." + stem + ".name", fallback=title(stem) + "输出模块")
    if stem in ITEM_NAME_MAP:
        key, fallback = ITEM_NAME_MAP[stem]
        return tr(key, fallback=fallback)
    return title(stem)


def item_category(rel):
    if rel.startswith("crafting/"):
        return "材料与核心"
    if rel.startswith("solar_panel_"):
        return "模块化太阳能"
    if "helmet" in rel or "gravi_" in rel:
        return "量子装备"
    if any(word in rel for word in ["saber", "drill", "rotor"]):
        return "工具与动力"
    if any(word in rel for word in ["fuel_rod", "heat_storage", "battery"]):
        return "反应堆与储能"
    return "其他组件"


def metric(label, value):
    return {"label": label, "value": value}


def item_details(rel):
    stem = Path(rel).stem
    if rel.startswith("solar_panel_glass/"):
        return {
            "day": [metric("白昼发电倍率", "+100%"), metric("夜间发电倍率", "+0%"), metric("插槽", "玻璃组件")],
            "night": [metric("白昼发电倍率", "+0%"), metric("夜间发电倍率", "+35%"), metric("插槽", "玻璃组件")],
            "day_night": [metric("白昼发电倍率", "+65%"), metric("夜间发电倍率", "+45%"), metric("插槽", "玻璃组件")],
            "rain": [metric("降雨发电倍率", "+35%（雨天基础倍率为65%）"), metric("插槽", "玻璃组件")],
        }[stem]
    if rel.startswith("solar_panel_battery/"):
        return {
            "basic": [metric("额外容量", "10,000 EU"), metric("插槽", "电池组件"), metric("等级", "基础")],
            "advanced": [metric("额外容量", "50,000 EU"), metric("插槽", "电池组件"), metric("等级", "进阶")],
            "quantum": [metric("额外容量", "250,000 EU"), metric("插槽", "电池组件"), metric("等级", "量子")],
        }[stem]
    if rel.startswith("solar_panel_output/"):
        return {
            "basic": [metric("额外输出", "32 EU/t"), metric("插槽", "输出组件"), metric("等级", "基础")],
            "advanced": [metric("额外输出", "128 EU/t"), metric("插槽", "输出组件"), metric("等级", "进阶")],
            "quantum": [metric("额外输出", "512 EU/t"), metric("插槽", "输出组件"), metric("等级", "量子")],
        }[stem]
    if stem in {"quantumsaber", "spectralsaber"}:
        return {
            "quantumsaber": [metric("电量", "300,000 EU"), metric("传输", "2,000 EU/t"), metric("攻击力", "11 / 29（右键）"), metric("IC2等级", "4")],
            "spectralsaber": [metric("电量", "600,000 EU"), metric("传输", "2,000 EU/t"), metric("攻击力", "14 / 39（右键）"), metric("IC2等级", "5")],
        }[stem]
    if stem == "spectral_battery":
        return [metric("容量", "100,000,000 EU"), metric("充放电", "30,000 EU/t"), metric("IC2等级", "4")]
    if "fuel_rod" in stem:
        if stem.startswith("depleted_"):
            return [metric("状态", "耗尽燃料棒"), metric("用途", "反应堆燃料耗尽后的产物")]
        cells = {"proton_fuel_rod": 1, "dual_proton_fuel_rod": 2, "quad_proton_fuel_rod": 4, "eit_proton_fuel_rod": 8}.get(stem, 1)
        return [metric("燃料单元", str(cells) + " 倍"), metric("耐久", "30,000 tick（配置值）"), metric("类型", "质子燃料")]
    if stem in {"twelve_heat_storage", "max_heat_storage"}:
        return [metric("热容量", "120,000" if stem == "twelve_heat_storage" else "240,000"), metric("用途", "核反应堆热量储存")]
    if stem in {"rotor_carbon1", "rotor_carbon2", "rotor_carbon3"}:
        info = {"rotor_carbon1": ("2×", "648,000 tick"), "rotor_carbon2": ("3×", "720,000 tick"), "rotor_carbon3": ("5×", "720,000 tick")}[stem]
        return [metric("风力效率", info[0]), metric("耐久", info[1]), metric("风强范围", "25–110"), metric("采样半径", "11")]
    if stem == "item_ult_drill":
        return [metric("IC2等级", "8"), metric("普通模式", "35速度 / 160 EU"), metric("3×3模式", "12速度 / 160 EU"), metric("5×5模式", "16速度 / 160 EU")]
    if stem in {"spectral_solar_helmet", "singular_solar_helmet", "advanced_solar_helmet", "hybrid_solar_helmet", "ultimate_solar_helmet"}:
        specs = {
            "spectral_solar_helmet": (8192, 5000, 0, 0, 9),
            "singular_solar_helmet": (131072, 104857, 0, 0, 9),
            "advanced_solar_helmet": (4, 4, 300000, 0, 3),
            "hybrid_solar_helmet": (32, 8, 600000, 0, 4),
            "ultimate_solar_helmet": (256, 64, 600000, 0, 4),
        }[stem]
        return [metric("白昼产能", f"{specs[0]:,} EU/t"), metric("夜间产能", f"{specs[1]:,} EU/t"), metric("电量", f"{specs[2]:,} EU"), metric("传输", f"{specs[3]:,} EU/t（源码字段当前为默认值）"), metric("IC2等级", str(specs[4]))]
    if stem == "helmet_solar_helmet":
        return [metric("类型", "可充电量子头盔"), metric("电量", "100,000,000 EU"), metric("传输", "100,000 EU/t"), metric("IC2等级", "8"), metric("护甲耗能", "1,000 EU / 次")]
    if stem in {"gravi_boosts", "gravi_leggins", "gravi_chestplate"}:
        effect = {"gravi_boosts": "水下呼吸 / 跳跃提升 / 生命恢复", "gravi_leggins": "速度 / 幸运", "gravi_chestplate": "火焰抗性"}[stem]
        return [metric("类型", "可充电量子装备"), metric("电量", "100,000,000 EU"), metric("传输", "100,000 EU/t"), metric("IC2等级", "8"), metric("特殊效果", effect)]
    if rel.startswith("crafting/"):
        return [metric("类型", "材料 / 组件"), metric("独立性能", "无；作为其他物品或机器的输入")]
    return [metric("类型", "物品组件"), metric("独立性能", "未声明独立发电、储能或输出参数")]


PANEL_SPECS = {
    "advanced": (5, 2.5, 3200, 1, 10),
    "hybrid": (20, 10, 20000, 2, 40),
    "ultimate": (80, 40, 200000, 3, 160),
    "quantum": (320, 160, 1000000, 4, 640),
    "spectral": (1280, 640, 5000000, 5, 2560),
    "proton": (5120, 2560, 50000000, 6, 10240),
    "singular": (20480, 10240, 1000000000, 7, 40960),
    "admin": (81920, 40960, 1500000000, 8, 163840),
    "photonic": (327680, 163840, 5000000000, 9, 655360),
    "neutronium": (1310720, 655360, 6500000000, 10, 2621440),
    "barion": (5242880, 2621440, 10000000000, 11, 10485760),
    "hadron": (20971520, 10485760, 25000000000, 12, 41943040),
    "graviton": (83886080, 41943040, 250000000000, 13, 167772160),
    "quark": (335544320, 167772160, 2500000000000, 14, 671088640),
}
def panel_info(name):
    modes = [("_solar_panel", "常规")]
    for suffix, mode in modes:
        if not name.endswith(suffix):
            continue
        tier_name = name[:-len(suffix)]
        if tier_name not in PANEL_SPECS:
            continue
        day, night, storage, tier, output = PANEL_SPECS[tier_name]
        details = [metric("白昼发电", f"{day:,} EU/t"), metric("夜间发电", f"{night:,} EU/t"), metric("最大输出", f"{output:,} EU/t"), metric("储能", f"{storage:,} EU"), metric("IC2等级", str(tier))]
        return "太阳能板 · " + mode, details
    return None


def block_name(name):
    return tr("super_solar_panels_rioutouscraft.machines." + name, fallback=title(name))


def block_details(name):
    panel = panel_info(name)
    if panel:
        return panel
    if name in {"advancedmfsu", "ultimatemfsu", "quantummfsu"}:
        values = {"advancedmfsu": (16384, 100000000, 5), "ultimatemfsu": (65536, 400000000, 6), "quantummfsu": (262144, 1600000000, 7)}[name]
        return "储能设备", [metric("输出", f"{values[0]:,} EU/t"), metric("容量", f"{values[1]:,} EU"), metric("IC2等级", str(values[2]))]
    if name.startswith("transformator"):
        tier = {"transformator": 5, "transformator1": 6, "transformator2": 7, "transformator3": 8}[name]
        return "变压器", [metric("IC2等级", str(tier)), metric("用途", "在对应电压等级之间转换电力"), metric("方向", "依照方块朝向输入 / 输出")]
    if name == "nutronfabricator":
        return "机器", [metric("机器等级", "14"), metric("默认参数", "1 mB中子流体 = 15,625,000 EU"), metric("用途", "生产中子流体")]
    if name == "molecular_transformer":
        return "机器", [metric("用途", "按分子转换配方转换物品"), metric("能耗", "由具体配方的 total EU 决定"), metric("配方", "配置文件可扩展")]
    return "机器与设备", [metric("硬度", "3.0"), metric("工具", "镐"), metric("说明", "具体行为由对应方块实体决定")]


def entry(entry_id, name, category, kind, registry, model, image, details, tags):
    power = 0
    for detail in details:
        if "EU/t" in detail["value"]:
            numbers = re.sub(r"[^0-9]", "", detail["value"])
            power = max(power, int(numbers or 0))
    return {"id": entry_id, "name": name, "category": category, "kind": kind, "registry": registry, "model": model, "image": image_data(image), "details": details, "tags": tags, "sortValue": power}


def block_model_path(name):
    stem = name
    if stem.endswith("_panelmoon"):
        stem = stem[:-4] + "1"
    if "singular" in stem:
        stem = stem.replace("singular", "singulary")
    path = BLOCK_MODELS / (stem + ".json")
    return path if path.exists() else BLOCK_MODELS / (name + ".json")


def build_entries():
    entries = []
    for model in sorted(ITEM_MODELS.rglob("*.json")):
        rel = model.relative_to(ITEM_MODELS).as_posix()
        if rel in {"default.json", "module.json", "spectral_battery1.json", "uu_matter1.json", "iridiumore.json", "platiumore.json"}:
            continue
        if rel.endswith("fluid.json") or rel in {"iridium_block.json", "iridium_ore.json", "platium_block.json", "platium_ore.json"}:
            continue
        entries.append(entry("item:" + rel, item_name(rel), item_category(rel), "物品", MODID + ":" + rel[:-5], "item/" + rel, model_texture(model), item_details(rel), [item_category(rel), Path(rel).stem]))

    java = (ROOT / "src/main/java/com/rioutouscraft/ssp/tiles/SSPBlock.java").read_text(encoding="utf-8")
    section = java.split("public enum SSPBlock implements", 1)[1].split("private final Class", 1)[0]
    block_names = re.findall(r"^\s*([A-Za-z0-9_]+)\(", section, re.MULTILINE)
    for name in block_names:
        category, details = block_details(name)
        model = block_model_path(name)
        entries.append(entry("block:" + name, block_name(name), category, "方块设备", MODID + ":" + name, "block/" + model.name, model_texture(model, True), details, [category, name]))

    for name, fallback in [("iridium_ore", "铱矿"), ("iridium_block", "铱块"), ("platium_ore", "铂矿"), ("platium_block", "铂块")]:
        model = BLOCK_MODELS / (name + ".json")
        entries.append(entry("block:" + name, tr("tile." + name + ".name", fallback=fallback), "资源与方块", "方块物品", MODID + ":" + name, "block/" + name + ".json", model_texture(model, True), [metric("类型", "可放置方块"), metric("用途", "世界生成 / 建筑材料"), metric("独立性能", "无发电或储能参数")], ["资源与方块", name]))
    return entries


CSS = r"""
:root{--bg:#0a0d14;--panel:#111722;--panel2:#151d2a;--line:#273246;--text:#eaf1ff;--muted:#93a2b8;--cyan:#52d8ff;--gold:#ffcf6b;--shadow:0 20px 60px rgba(0,0,0,.28)}
*{box-sizing:border-box}body{margin:0;background:radial-gradient(circle at 10% -10%,#203152 0,transparent 40%),radial-gradient(circle at 90% 0,#2e244a 0,transparent 38%),var(--bg);color:var(--text);font:14px/1.6 Inter,"Segoe UI","Microsoft YaHei",sans-serif;min-height:100vh}button,input,select{font:inherit;color:inherit}.app{max-width:1500px;margin:0 auto;padding:34px 28px 70px}.hero{display:flex;justify-content:space-between;gap:30px;align-items:flex-end;padding:34px 38px 32px;border:1px solid #334258;border-radius:24px;background:linear-gradient(135deg,rgba(34,51,78,.86),rgba(17,23,34,.86));box-shadow:var(--shadow);position:relative;overflow:hidden}.eyebrow{letter-spacing:.18em;text-transform:uppercase;color:var(--cyan);font-size:12px;font-weight:700}.hero h1{font-size:clamp(26px,4vw,48px);line-height:1.1;margin:10px 0 12px;letter-spacing:-.03em}.hero p{margin:0;color:#b6c4d9;max-width:740px}.hero-badge{min-width:180px;padding:16px 18px;border-radius:16px;background:rgba(8,13,22,.68);border:1px solid #405475}.hero-badge strong{display:block;font-size:30px;color:var(--gold);line-height:1}.hero-badge span{color:var(--muted);font-size:12px}.stats{display:grid;grid-template-columns:repeat(4,1fr);gap:13px;margin:22px 0}.stat{background:rgba(17,23,34,.76);border:1px solid var(--line);border-radius:16px;padding:16px 18px}.stat b{display:block;font-size:24px;color:var(--cyan)}.stat span{color:var(--muted);font-size:12px}.toolbar{display:flex;gap:12px;align-items:center;flex-wrap:wrap;margin:22px 0 12px}.search{flex:1 1 330px;position:relative}.search input{width:100%;background:var(--panel);border:1px solid var(--line);border-radius:13px;padding:12px 15px 12px 42px;outline:none}.search input:focus{border-color:var(--cyan);box-shadow:0 0 0 3px rgba(82,216,255,.12)}.search:before{content:"⌕";position:absolute;left:15px;top:6px;font-size:24px;color:var(--muted)}select{background:var(--panel);border:1px solid var(--line);border-radius:13px;padding:12px 38px 12px 14px;outline:none}.filters{display:flex;gap:8px;flex-wrap:wrap;margin:10px 0 24px}.filter{cursor:pointer;border:1px solid var(--line);background:rgba(17,23,34,.7);padding:7px 12px;border-radius:99px;color:var(--muted);transition:.18s}.filter:hover,.filter.active{color:#061018;background:var(--cyan);border-color:var(--cyan)}.filter em{font-style:normal;margin-left:5px;opacity:.65}.catalog{display:grid;grid-template-columns:repeat(auto-fill,minmax(235px,1fr));gap:14px}.card{border:1px solid var(--line);background:linear-gradient(180deg,rgba(21,29,42,.94),rgba(14,19,29,.94));border-radius:17px;padding:14px;min-height:316px;display:flex;flex-direction:column;cursor:pointer;transition:transform .18s,border-color .18s,box-shadow .18s}.card:hover{transform:translateY(-4px);border-color:#4d719c;box-shadow:0 13px 35px rgba(0,0,0,.27)}.card-top{display:flex;justify-content:space-between;gap:8px;align-items:center;margin-bottom:10px}.pill{font-size:11px;border:1px solid #35455d;color:var(--muted);border-radius:99px;padding:2px 7px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis}.pill.kind{color:var(--gold);border-color:#635432}.icon{height:150px;border:1px solid #2a3950;border-radius:13px;background:repeating-conic-gradient(#172131 0 25%,#131b28 0 50%) 50%/18px 18px;display:grid;place-items:center;overflow:hidden;margin-bottom:12px}.icon img{width:110px;height:110px;image-rendering:pixelated;object-fit:contain;filter:drop-shadow(0 9px 10px rgba(0,0,0,.3))}.placeholder{width:100px;height:100px;border-radius:25px;display:grid;place-items:center;text-align:center;padding:12px;background:linear-gradient(135deg,#23435e,#392b64);color:#d8efff;font-size:12px}.card h2{font-size:16px;line-height:1.35;margin:0 0 4px}.registry{font-size:10px;color:#718096;white-space:nowrap;overflow:hidden;text-overflow:ellipsis}.metrics{margin:11px 0 0;padding:0;list-style:none;display:flex;gap:5px;flex-wrap:wrap}.metrics li{font-size:11px;color:#bdc9dc;background:#192333;border:1px solid #2a3b54;padding:2px 6px;border-radius:5px}.empty{text-align:center;padding:70px 20px;color:var(--muted);display:none}.modal-wrap{position:fixed;inset:0;background:rgba(2,5,10,.74);backdrop-filter:blur(8px);display:none;place-items:center;padding:20px;z-index:5}.modal-wrap.open{display:grid}.modal{width:min(760px,100%);max-height:min(820px,95vh);overflow:auto;border:1px solid #405475;border-radius:22px;background:#111824;box-shadow:0 30px 100px rgba(0,0,0,.5);padding:22px}.modal-head{display:flex;justify-content:space-between;gap:12px;align-items:flex-start}.close{border:1px solid var(--line);background:#1a2434;border-radius:9px;width:34px;height:34px;cursor:pointer;font-size:20px}.modal-main{display:grid;grid-template-columns:190px 1fr;gap:22px;margin-top:16px}.modal .icon{height:190px}.modal .icon img{width:140px;height:140px}.modal h2{font-size:26px;line-height:1.2;margin:4px 0 6px}.modal .registry{white-space:normal;overflow-wrap:anywhere}.details{border-top:1px solid var(--line);margin-top:22px;padding-top:16px}.detail-row{display:grid;grid-template-columns:150px 1fr;gap:12px;border-bottom:1px solid #202d40;padding:9px 0}.detail-row:last-child{border-bottom:0}.detail-row b{color:#91a5c1;font-weight:600}.detail-row span{color:#e9f1ff}.footnote{margin-top:18px;font-size:12px;color:#75859d}.footer{margin-top:30px;color:#6f7e94;font-size:12px;text-align:center}@media(max-width:720px){.app{padding:16px 13px 50px}.hero{padding:24px 20px;display:block}.hero-badge{margin-top:20px}.stats{grid-template-columns:repeat(2,1fr)}.modal-main{grid-template-columns:1fr}.modal .icon{height:150px}.detail-row{grid-template-columns:120px 1fr}}
"""


def build_html(items):
    data = json.dumps(items, ensure_ascii=False, separators=(",", ":"))
    categories = Counter(item["category"] for item in items)
    category_data = json.dumps(dict(sorted(categories.items())), ensure_ascii=False, separators=(",", ":"))
    return """<!doctype html>
<html lang="zh-CN"><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<title>SuperSolarPanels-RioutousCraft · 物品图鉴</title><style>""" + CSS + """</style></head><body><div class="app">
<header class="hero"><div><div class="eyebrow">Minecraft 1.12.2 · IC2 2.8.222</div><h1>SuperSolarPanels-RioutousCraft<br>物品图鉴</h1><p>按当前源码中的物品模型、注册方块和配置参数生成。支持搜索、分类筛选与详情查看。</p></div><div class="hero-badge"><strong id="heroCount">""" + str(len(items)) + """</strong><span>条目已收录</span></div></header>
<section class="stats"><div class="stat"><b id="statVisible">""" + str(len(items)) + """</b><span>当前显示</span></div><div class="stat"><b>""" + str(len(categories)) + """</b><span>物品分类</span></div><div class="stat"><b>""" + str(sum(1 for item in items if item["image"])) + """</b><span>已嵌入贴图</span></div><div class="stat"><b>EU/t</b><span>发电与输出参数单位</span></div></section>
<section class="toolbar"><label class="search"><input id="search" type="search" placeholder="搜索名称、注册名或参数…" autocomplete="off"></label><select id="sort"><option value="default">默认顺序</option><option value="name">名称排序</option><option value="power">输出参数从高到低</option></select></section><nav class="filters" id="filters"></nav><main class="catalog" id="catalog"></main><div class="empty" id="empty">没有找到匹配条目，换一个关键词试试。</div><div class="footer">数据来源：当前 SuperSolarPanels-RioutousCraft 源码与资源文件；页面为本地静态文件，无外部依赖。</div></div>
<div class="modal-wrap" id="modalWrap"><section class="modal" role="dialog" aria-modal="true"><div class="modal-head"><div><div class="eyebrow" id="modalKind"></div><h2 id="modalTitle"></h2></div><button class="close" id="closeModal" aria-label="关闭">×</button></div><div class="modal-main"><div class="icon" id="modalIcon"></div><div><div class="pill" id="modalCategory"></div><p class="registry" id="modalRegistry"></p><div class="details" id="modalDetails"></div></div></div><div class="footnote" id="modalModel"></div></section></div>
<script>
const ITEMS=""" + data + """;
const CATEGORIES=""" + category_data + """;
const state={query:"",category:"全部",sort:"default"};
const $=function(id){return document.getElementById(id)};
const esc=function(value){return String(value==null?"":value).replace(/[&<>"']/g,function(c){return {"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"}[c]})};
function icon(item){return item.image?"<img src='"+item.image+"' alt='"+esc(item.name)+"'>":"<div class='placeholder'>"+esc(item.name.slice(0,10))+"</div>"}
function card(item){var preview=item.details.slice(0,3).map(function(d){return "<li>"+esc(d.label)+"："+esc(d.value)+"</li>"}).join("");return "<article class='card' data-id='"+esc(item.id)+"' tabindex='0'><div class='card-top'><span class='pill'>"+esc(item.category)+"</span><span class='pill kind'>"+esc(item.kind)+"</span></div><div class='icon'>"+icon(item)+"</div><h2>"+esc(item.name)+"</h2><div class='registry'>"+esc(item.registry)+"</div><ul class='metrics'>"+preview+"</ul></article>"}
function filtered(){var list=ITEMS.filter(function(item){var hay=[item.name,item.category,item.registry,item.model].concat(item.tags).concat(item.details.map(function(d){return d.label+" "+d.value})).join(" ").toLowerCase();return (state.category==="全部"||item.category===state.category)&&(!state.query||hay.indexOf(state.query.toLowerCase())>=0)});if(state.sort==="name")list.sort(function(a,b){return a.name.localeCompare(b.name,"zh")});if(state.sort==="power")list.sort(function(a,b){return b.sortValue-a.sortValue});return list}
function render(){var list=filtered();$("catalog").innerHTML=list.map(card).join("");$("empty").style.display=list.length?"none":"block";$("statVisible").textContent=list.length;document.querySelectorAll(".card").forEach(function(card){card.addEventListener("click",function(){openModal(card.dataset.id)});card.addEventListener("keydown",function(e){if(e.key==="Enter"||e.key===" ")openModal(card.dataset.id)})})}
function renderFilters(){var all=["全部"].concat(Object.keys(CATEGORIES));$("filters").innerHTML=all.map(function(category){return "<button class='filter "+(state.category===category?"active":"")+"' data-category='"+esc(category)+"'>"+esc(category)+" <em>"+(category==="全部"?ITEMS.length:CATEGORIES[category])+"</em></button>"}).join("");document.querySelectorAll(".filter").forEach(function(button){button.addEventListener("click",function(){state.category=button.dataset.category;renderFilters();render()})})}
function openModal(id){var item=ITEMS.find(function(value){return value.id===id});if(!item)return;$("modalKind").textContent=item.kind;$("modalTitle").textContent=item.name;$("modalCategory").textContent=item.category;$("modalRegistry").textContent=item.registry;$("modalModel").textContent="资源模型："+item.model;$("modalIcon").innerHTML=icon(item);$("modalDetails").innerHTML=item.details.map(function(d){return "<div class='detail-row'><b>"+esc(d.label)+"</b><span>"+esc(d.value)+"</span></div>"}).join("");$("modalWrap").classList.add("open")}
function closeModal(){$("modalWrap").classList.remove("open")}
$("search").addEventListener("input",function(e){state.query=e.target.value.trim();render()});$("sort").addEventListener("change",function(e){state.sort=e.target.value;render()});$("closeModal").addEventListener("click",closeModal);$("modalWrap").addEventListener("click",function(e){if(e.target.id==="modalWrap")closeModal()});document.addEventListener("keydown",function(e){if(e.key==="Escape")closeModal()});renderFilters();render();
</script></body></html>"""


if __name__ == "__main__":
    items = build_entries()
    OUT.write_text(build_html(items), encoding="utf-8")
    print("wrote", OUT, len(items), "entries,", len(Counter(item["category"] for item in items)), "categories")
