package com.wille.taskvars

import com.intellij.codeInsight.template.Expression
import com.intellij.codeInsight.template.ExpressionContext
import com.intellij.codeInsight.template.macro.MacroBase
import com.intellij.codeInsight.template.Result
import com.intellij.codeInsight.template.TextResult
import com.intellij.openapi.project.Project
import com.intellij.tasks.TaskManager

/**
 * Live template macro that returns the current task's summary (or empty string)
 * so it can be used inside live templates. Uses the type-safe TaskManager API.
 */
class CurrentTaskMacro : MacroBase("currentTask", "currentTask()") {
    // Required overload: (params, context)
    override fun calculateResult(params: Array<out Expression>, context: ExpressionContext): Result? {
        return calculateResult(params, context, false)
    }

    // Required overload: (params, context, quick)
    override fun calculateResult(params: Array<out Expression>, context: ExpressionContext, quick: Boolean): Result? {
        val project: Project? = context.project
        if (project == null) return TextResult("")

        val manager = TaskManager.getManager(project)
        val active = manager.activeTask
        val summary = active?.summary ?: ""
        return TextResult(summary)
    }

    override fun getDefaultValue(): String = ""
}

