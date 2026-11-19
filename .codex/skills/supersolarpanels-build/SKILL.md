---
name: supersolarpanels-build
description: Build this SuperSolarPanels-RioutousCraft Minecraft 1.12.2 mod with the machine-local JDK 8, Gradle cache, local Maven repository, and proxy fallback.
---

# Project build skill

Use this skill when building the mod in this repository. Keep all downloaded build dependencies under `/opt/minecraft-tools`.

## Environment

- Source repository: the directory containing this skill and `gradlew`.
- JDK 8: `/opt/minecraft-tools/jdk8`, loaded via `/opt/minecraft-tools/env-java8.sh`.
- Gradle user home/cache: `/opt/minecraft-tools/gradle-home`.
- Local Maven repository: `/opt/minecraft-tools/local-maven`.
- Available runtime/dependency mods: `/opt/minecraft-tools/mods/`.
- Download proxy when network access is needed: `http://192.168.30.118:7890`.

## Build procedure

From the repository root, load the JDK 8 environment and run:

```bash
source /opt/minecraft-tools/env-java8.sh
export GRADLE_USER_HOME=/opt/minecraft-tools/gradle-home
export HTTP_PROXY=http://192.168.30.118:7890
export HTTPS_PROXY=http://192.168.30.118:7890
export ALL_PROXY=http://192.168.30.118:7890
./gradlew build -PlocalMaven=/opt/minecraft-tools/local-maven --no-daemon
```

The build must use the repository's `local-maven` override pointing at `/opt/minecraft-tools/local-maven`. If a dependency is missing, download or copy it into `/opt/minecraft-tools` first, then rerun the build. Do not use a temporary directory or rely only on an uncached remote URL.

The distributable JAR is written to `build/libs/`. A successful build ends with `BUILD SUCCESSFUL`; run `git diff --check` after source changes.
