package ipn.upiita.mx.proyecto1.model

class TaskRepository(private val dao: TaskDao) {
        suspend fun insert(task: Task) = dao.InserTask(task)
        suspend fun getAll() = dao.getAllTask()
        suspend fun findByName(name: String) = dao.getTaskByName(name)
        suspend fun findByTaskId(taskId: Int) = dao.getTaskById(taskId)

}