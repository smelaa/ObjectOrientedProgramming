fun IWorldMap.randomPosition(mapSize: Vector2d): Vector2d?{
    var pos: ArrayList<Vector2d> = ArrayList<Vector2d>()
    for (i in 0 until (mapSize.x).toInt()){
        for (j in 0 until (mapSize.y).toInt())
        { pos.add(Vector2d(i*1.0, j*1.0)) }
    }
    return pos.randomOrNull()
}

fun IWorldMap.randomFreePosition(mapSize: Vector2d): Vector2d?{
    var emptyPos: ArrayList<Vector2d> = ArrayList<Vector2d>()
    for (i in 0 until (mapSize.x).toInt()){
        for (j in 0 until (mapSize.y).toInt())
        {
            if (!this.isOccupied(Vector2d(i* 1.0,j* 1.0))) {
                    emptyPos.add(Vector2d(i * 1.0, j * 1.0))
            }
        }
    }
    return emptyPos.randomOrNull()
}

