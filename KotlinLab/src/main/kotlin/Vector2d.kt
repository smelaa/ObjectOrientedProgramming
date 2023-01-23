import MapDirection


data class Vector2d (
    val x: Double,
    val y: Double,) //same here: jak zrobić public getter ale private setter
{
    operator fun Vector2d.unaryMinus() = Vector2d(-x, -y)
    operator fun Vector2d.plus(other: Vector2d) = this.add(other)
    operator fun Vector2d.minus(other: Vector2d) = this.substract(other)
    operator fun Vector2d.compareTo(other: Vector2d) = when(null){
        this.follows(other) -> 1
        this.precedes(other) -> -1
        else -> 0
    }

    fun precedes(other: Vector2d): Boolean{
        return x <= other.x && y <= other.y
    }

    fun follows(other: Vector2d): Boolean{
        return x >= other.x && y >= other.y
    }

    fun add(other: Vector2d): Vector2d{
        return Vector2d(x + other.x, y + other.y)
    }

    fun substract(other: Vector2d): Vector2d{
        return Vector2d(x - other.x, y - other.y)
    }

    fun upperRight(other: Vector2d): Vector2d{
        return Vector2d(Math.max(x, other.x), Math.max(y, other.y))
    }

    fun lowerRight(other: Vector2d): Vector2d{
        return Vector2d(Math.min(x, other.x), Math.min(y, other.y))
    }

    fun opposite(): Vector2d{
        return Vector2d(-x,-y)
    }
}

fun MapDirection.toUnitVector() : Vector2d = when(this){
    MapDirection.NORTH -> Vector2d(0.0,1.0)
    MapDirection.SOUTH -> Vector2d(0.0,-1.0)
    MapDirection.WEST -> Vector2d(-1.0,0.0)
    MapDirection.EAST -> Vector2d(1.0,0.0)

}

