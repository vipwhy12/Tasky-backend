package org.example.todo.infrastructure.mcp

import org.example.todo.application.usecase.ManageTaskUseCase
import org.example.todo.application.dto.TaskDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/mcp")
class McpController(
    private val useCase: ManageTaskUseCase
) {
    data class ToolDescriptor(
        val name: String,
        val title: String,
        val description: String,
        val inputSchema: Map<String, Any>,
        val outputSchema: Map<String, Any>,
        val _meta: Map<String, Any>
    )

    // ✅ ChatGPT가 이 응답을 JSON으로 받게 지정
    @GetMapping("/tools", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listTools(): List<ToolDescriptor> {
        return listOf(
            ToolDescriptor(
                name = "tasksList",
                title = "List Tasks",
                description = "Fetch all tasks",
                inputSchema = mapOf("type" to "object"),
                outputSchema = mapOf(
                    "type" to "object",
                    "properties" to mapOf(
                        "tasks" to mapOf("type" to "array", "items" to mapOf("type" to "object"))
                    )
                ),
                _meta = mapOf(
                    "openai/outputTemplate" to "ui://widget/tasksList.html",
                    "openai/widgetAccessible" to true
                )
            )
        )
    }

    data class TasksListOutput(val tasks: List<TaskDto>)

    @PostMapping("/call/tasksList", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun callTasksList(@RequestBody params: Map<String, Any>): TasksListOutput {
        val tasks = useCase.getTasks().map {
            TaskDto(
                id = it.id,
                title = it.title,
                dueDate = it.dueDate,
                priority = it.priority,
                category = it.category,
                isCompleted = it.isCompleted
            )
        }
        return TasksListOutput(tasks)
    }
}
