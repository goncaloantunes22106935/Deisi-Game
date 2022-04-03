package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType { POST, GET }

fun router() : Function1<CommandType,Function2<GameManager,List<String>,String?>>{
    return ::command
}

fun command(commandType:CommandType) : Function2<GameManager,List<String>,String?>{
    return when (commandType){
        CommandType.POST -> ::commandPost
        CommandType.GET -> ::commandGet
    }
}

fun commandGet(manager: GameManager, args: List<String>): String?{
    return when (args[0]){
        "PLAYER" -> getPlayer(manager,args)
        "PLAYERS_BY_LANGUAGE" -> getPlayersByLanguages(manager,args)
        "POLYGLOTS" -> manager.players.filter{it.programmerFavLanList.size > 1}.sortedWith(Comparator<Programmer>{ a, b ->
            when {
                a.programmerFavLanList.size > b.programmerFavLanList.size -> 1
                a.programmerFavLanList.size < b.programmerFavLanList.size -> -1
                else -> 0
            }
        }).joinToString {it.name +":"+ it.programmerFavLanList.size+"\n"}.replace(", ","").dropLast(1)
        "MOST_USED_POSITIONS" ->manager.positions.sortedWith(Comparator<Position>{ a, b ->
            when {
                a.position < b.position -> 1
                a.position > b.position -> -1
                else -> 0
            }
        }).take(Integer.parseInt(args[1])).joinToString { "${manager.positions.indexOf(it)}:${it.position}\n" }.replace(", ","").trim()
        "MOST_USED_ABYSSES" -> manager.abyssesPositions.sortedWith(Comparator<Position>{ a, b ->
            when {
                a.position < b.position -> 1
                a.position > b.position -> -1
                else -> 0
            }
        }).take(Integer.parseInt(args[1])).joinToString { "${it.abyss}:${it.position}\n" }.replace(", ","").trim()
        else -> null
    }
}

fun commandPost(manager: GameManager, args: List<String>): String?{
    return when (args[0]){
        "MOVE" -> move(manager,args)
        "ABYSS" -> addAbyss(manager,args)
        else -> null
    }
}

fun getPlayersByLanguages(manager: GameManager, args: List<String>):String?{
    var ans: String
    ans = manager.players.filter{it.programmerFavLanList.contains(args[1])}.joinToString {it.name +","}
    return ans.replace(", ","").dropLast(1)
}

fun getPlayer(manager: GameManager, args: List<String>):String?{
    var ans: String
    ans = manager.players.filter{it.name.contains(args[1])}.take(1).toString().replace("[","").replace("]","")
    if (ans == "")
        ans = "Inexistent player"
    return ans
}

fun move(manager: GameManager, args: List<String>):String?{
    if(!manager.moveCurrentPlayer(Integer.parseInt(args[1]))){
        return null
    }
    var answer: String? = manager.reactToAbyssOrTool()
    if(answer == null){
        return "OK"
    }
    return answer
}

fun addAbyss(manager: GameManager, args: List<String>):String?{
    var abyss: Abyss? = null
    val effectPosition:Int = Integer.parseInt(args[2])
    val effectId:Int = Integer.parseInt(args[1])

    manager.tools.forEach {if(it.position == effectPosition){
        return "Position is occupied"
    } }

    manager.abysses.forEach {if(it.position == effectPosition){
        return "Position is occupied"
    } }
    when (effectId) {
        0 -> abyss = SyntaxError(effectId, effectPosition)
        1 -> abyss = LogicError(effectId, effectPosition)
        2 -> abyss = ExceptionError(effectId, effectPosition)
        3 -> abyss = FileNotFoundError(effectId, effectPosition)
        4 -> abyss = CrashError(effectId, effectPosition)
        5 -> abyss = DuplicatedCode(effectId, effectPosition)
        6 -> abyss = SecundaryEffects(effectId, effectPosition)
        7 -> abyss = BlueScreenError(effectId, effectPosition)
        8 -> abyss = InfiniteCicle(effectId, effectPosition)
        9 -> abyss = SegmentationFault(effectId, effectPosition)
    }
    manager.abysses.add(abyss)
    return "OK"
}