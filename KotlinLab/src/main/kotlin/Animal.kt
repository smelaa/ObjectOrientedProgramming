class Animal (
    var direction: MapDirection = MapDirection.NORTH,
    private val map: IWorldMap? = null,
    var position: Vector2d = Vector2d(2.0,2.0)
            //private set - how??
        )
{
    override fun toString(): String = when (direction) {
            MapDirection.EAST -> "E"
            MapDirection.WEST -> "W"
            MapDirection.NORTH -> "N"
            MapDirection.SOUTH -> "S"
            else -> ""
        }


    fun isAt(pos: Vector2d?): Boolean {
        return position==pos
    }

    fun move(direction: MoveDirection) {
        when (direction) {
            MoveDirection.RIGHT -> this.direction = this.direction.next()
            MoveDirection.LEFT -> this.direction = this.direction.previous()
            else -> {
                var directionVector: Vector2d = this.direction.toUnitVector()
                if (direction === MoveDirection.BACKWARD) {
                    directionVector = directionVector.opposite()
                }
                val newLocation: Vector2d = position.add(directionVector)
                if (map != null && map!!.canMoveTo(newLocation)) {
                    position = newLocation
                }
                else if (map == null){
                    position = newLocation
                }
            }
        }
    }
}