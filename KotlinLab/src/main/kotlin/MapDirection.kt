

enum class MapDirection {
    NORTH, SOUTH, WEST, EAST;


    override fun toString(): String = when(this){
            NORTH -> "Północ"
            SOUTH -> "Południe"
            WEST -> "Zachód"
            EAST -> "Wschód"
        }

    fun next(): MapDirection = when(this){
        NORTH -> EAST;
        SOUTH -> WEST;
        WEST -> NORTH;
        EAST -> SOUTH;
    }
    fun previous(): MapDirection = when(this){
        NORTH -> WEST;
        SOUTH -> EAST;
        WEST -> SOUTH;
        EAST -> NORTH;
    }

}