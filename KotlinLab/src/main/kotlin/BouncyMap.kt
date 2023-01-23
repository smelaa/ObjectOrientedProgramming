class BouncyMap (
    private val height: Int = 0,
    private val width: Int = 0
) : IWorldMap
{
    private var animals: HashMap<Vector2d,Animal> = HashMap()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.precedes(Vector2d(width*1.0,height*1.0)) && position.follows(Vector2d(0.0,0.0))
    }

    override fun place(animal: Animal): Boolean {
        if(animals.containsValue(animal)){
            val animalsPositions: MutableSet<Vector2d> = HashSet<Vector2d>()
            animalsPositions.addAll(animals.keys)
            for (pos in animalsPositions){
                if (animals.get(pos)===animal){
                    animals.remove(pos)
                    break
                }
            }
        }
        return putOnMap(animal)
    }

    private fun putOnMap(animal:Animal): Boolean{
        //jak to zrobić lepiej?
        if(!isOccupied(animal.position) and canMoveTo(animal.position)) {
                animals.put(animal.position, animal)
                return true
        }
        else {
            val randomFree: Vector2d? = this.randomFreePosition(Vector2d(width*1.0,height*1.0))
            if (randomFree != null){
                animal.position = randomFree!!
                animals.put(animal.position, animal)
                return true
            }
            else {
                val randomSpot: Vector2d? = this.randomPosition(Vector2d(width*1.0,height*1.0))
                if (randomSpot!= null){
                    animal.position = randomSpot!!
                    return true
                }
                else {return false}
            }
        }
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return animals.containsKey(position)
    }

    override fun objectAt(position: Vector2d): Animal? {
        if (isOccupied(position)) return animals[position]
        else return null
    }
}