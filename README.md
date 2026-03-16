# TaskVars - IntelliJ IDEA 插件

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-2025.3.3-blueviolet)

## 📋 项目简介

TaskVars 是一个为 IntelliJ IDEA 开发的轻量级插件，它提供了一个**实时模板宏（Live Template Macro）**，可以在代码模板中自动插入当前任务的摘要信息。

## ✨ 核心功能

- **任务信息插入**：在实时模板中使用 `currentTask()` 宏，自动获取并插入当前激活任务的摘要
- **无缝集成**：与 IntelliJ IDEA 内置的 Tasks 插件完美配合
- **简洁高效**：无额外 UI 组件，不影响 IDE 性能

## 🚀 使用方法

### 前提条件

1. 已在 IntelliJ IDEA 中启用并配置 **Tasks** 插件（默认已启用）
2. 已创建或打开一个任务（通过 **Tools → Tasks & Contexts → Open Task**）

### 配置实时模板

1. 打开 **Settings/Preferences** (`Ctrl+Alt+S`)
2. 导航到 **Editor → Live Templates**
3. 选择或创建一个模板组
4. 添加新模板，例如：
   - **缩写**: `tasklog`
   - **模板正文**: 
     ```
     // Task: $TASK$
     $END$
     ```
   - 点击 **Edit variables** 按钮
   - 将 `TASK` 变量的表达式设置为 `currentTask()`
5. 应用设置并保存

### 使用示例

1. 在编辑器中输入模板缩写 `tasklog`
2. 按下 `Tab` 键展开模板
3. 自动生成的内容将包含当前任务的摘要，例如：
   ```
   // Task: Fix login bug
   ```

## 🔨 开发与构建

### 环境要求

- **JDK**: 21 或更高版本
- **Gradle**: 使用项目内置的 Gradle Wrapper
- **IntelliJ IDEA**: 2025.3.3 或兼容版本

### 构建项目

```bash
# 克隆项目
git clone <repository-url>
cd TaskVars

# 构建插件
./gradlew build

# 构建并运行测试
./gradlew check
```

### 运行插件

在 IntelliJ IDEA 中：

1. 打开项目
2. 使用预定义的运行配置 **Run Plugin**（或执行 `./gradlew runIde`）
3. 将在新的 IDE 实例中运行插件，可用于调试

### 运行测试

```bash
./gradlew test
```

## 📦 插件安装

### 方式一：从本地文件安装

1. 在项目根目录执行构建：
   ```bash
   ./gradlew buildPlugin
   ```
2. 生成的插件文件位于 `build/distributions/TaskVars-1.0-SNAPSHOT.zip`
3. 在 IntelliJ IDEA 中：
   - **Settings/Preferences → Plugins → ⚙️ → Install Plugin from Disk**
   - 选择生成的 ZIP 文件
   - 重启 IDE

### 方式二：发布到 JetBrains Marketplace

参考官方文档：[Publishing a Plugin](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html)

## 📁 项目结构

```
TaskVars/
├── src/
│   └── main/
│       ├── kotlin/                    # Kotlin 源代码
│       │   └── com/wille/taskvars/
│       │       └── CurrentTaskMacro.kt
│       └── resources/
│           └── META-INF/
│               └── plugin.xml         # 插件配置文件
├── build.gradle.kts                   # Gradle 构建配置
├── settings.gradle.kts                # Gradle 项目设置
└── README.md                          # 项目说明文档
```

## ⚠️ 注意事项

- 该插件依赖内置的 **Tasks** 插件，请确保其已启用
- `currentTask()` 宏仅在激活任务时返回有效内容，否则返回空字符串
- 实时模板中的变量名称可以自定义，但表达式必须为 `currentTask()`

## 🔗 相关链接

- [IntelliJ Platform SDK 文档](https://plugins.jetbrains.com/docs/intellij)
- [实时模板宏开发指南](https://plugins.jetbrains.com/docs/intellij/live-templates.html#macro)
- [Tasks 插件 API 参考](https://plugins.jetbrains.com/docs/intellij/tasks-api.html)

## 📄 许可证

MIT License

---

**开发提示**：如需修改宏的行为（例如返回任务 ID、描述等），请编辑 `CurrentTaskMacro.kt` 中的 `calculateResult` 方法。