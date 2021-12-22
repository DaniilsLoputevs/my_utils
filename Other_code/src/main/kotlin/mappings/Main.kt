package mappings

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

val gson = Gson()


object Mappings {
    val mappings = mutableMapOf<String, Pair<Type, AppCtx.(Any) -> Any>>()
}

inline fun <reified T> path(path: String, noinline handle: AppCtx.(income: T) -> Any) {
    Mappings.mappings[path] = Pair(object : TypeToken<T>() {}.type, handle as AppCtx.(Any) -> Any)
}
inline fun <reified T, Y> path(path: String, noinline handle: AppCtx.(income: T, income1 : Y) -> Any) {
    Mappings.mappings[path] = Pair(object : TypeToken<T>() {}.type, handle as AppCtx.(Any) -> Any)
}
inline fun <reified T> pathCtx(path: String, noinline handle: AppCtx.(user : UserK, income: T) -> Any) {
    Mappings.mappings[path] = Pair(object : TypeToken<T>() {}.type, handle as AppCtx.(Any) -> Any)
}



object AppCtx {
//    init {
//        pathCtx<UserK>("") { user, income ->
//
//        }
//    }
    val repositories: Repositories = Repositories()
    val service: Services = Services()
    val controllers: Controllers = Controllers()

    class Repositories {
        lateinit var userRepo: UserRepository
    }

    class Services {
        lateinit var userServ: UserService
    }

    class Controllers {
        lateinit var userController: UserController
    }
}

fun controllers(config: AppCtx.Controllers.() -> Unit) = AppCtx.controllers.config()


class UserRepository {}
class UserService {}
class UserController {
    init {
        path<UserK>("testUser") {
            println("it work! $it")
        }
    }
}


data class UserK(val login: String, val password: String)



fun main() {
    controllers {
        userController = UserController()
    }
    userControllerPaths()

    // some code about WS ...

    val path = "testUser"
    val incomeBody = """
        {
            "login": "aaa",
            "password": "bbb"
        }
    """.trimIndent()

    val (type, handle) = Mappings.mappings[path] ?: throw IllegalArgumentException("path is not supported! path:$path")
    handle.invoke(AppCtx, gson.fromJson(incomeBody, type))


}

object Te {
    val a = FI
    val b = KIY
    init {
        println("Te init")
    }
}

object FI {
    init {
        println("FI init")
    }
}
object KIY {
    init {
        println("KIY init")
    }
}