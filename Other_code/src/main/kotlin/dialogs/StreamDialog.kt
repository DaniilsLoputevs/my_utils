package dialogs

class StreamDialog {
    var rootReplicaS = ReplicaS()


}

class ReplicaS {
    var uuid = ""
    var text = ""
    val nextDialogVariants = mutableMapOf<(Any) -> Boolean, Replica>()
    var onPlay: () -> Unit = {}

    fun answer(
        show: () -> Boolean = { true },
        next: ReplicaS.() -> Unit
    ) : ReplicaS {
        val rsl = ReplicaS()
        next.invoke(rsl);
        return rsl;
    }
}

fun main() {
    val talk = ReplicaS()
        .answer { text = "" }
        .answer {  }
        .answer {  }
}
