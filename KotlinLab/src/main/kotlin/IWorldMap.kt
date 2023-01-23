

interface IWorldMap
    {

        fun canMoveTo(position: Vector2d): Boolean

        fun place(animal: Animal): Boolean

        fun isOccupied(position: Vector2d): Boolean

        fun objectAt(position: Vector2d): Animal?

    }
