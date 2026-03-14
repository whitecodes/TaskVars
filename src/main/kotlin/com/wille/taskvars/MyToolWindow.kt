package com.wille.taskvars

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import javax.swing.JButton
import com.intellij.tasks.TaskManager

class MyToolWindowFactory : ToolWindowFactory {
    override fun shouldBeAvailable(project: Project) = true

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(project)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }
    class MyToolWindow(private val project: Project) {
        private fun getActiveTaskSummary(): String {
            val manager = TaskManager.getManager(project);
            val active = manager.activeTask
            return active?.summary ?: "(no active task)"
        }

        private val content = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel("Current task: ${getActiveTaskSummary()}")

            add(label)
            add(JButton("Refresh").apply {
                addActionListener {
                    label.text = "Current task: ${getActiveTaskSummary()}"
                }
            })
        }

        fun getContent(): JBPanel<JBPanel<*>> = content
    }
}