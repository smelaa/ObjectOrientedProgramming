import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MapTests : ShouldSpec({
    should("work well with 1 animal"){
        val map: BouncyMap = BouncyMap(3,3)
        val burek: Animal = Animal(position = Vector2d(0.0,0.0))
        map.place(burek) shouldBe true
        map.isOccupied(burek.position) shouldBe true
        map.objectAt(burek.position) shouldBe burek
        map.objectAt(Vector2d(2.0,2.0)) shouldBe null
        burek.move(MoveDirection.FORWARD)
        map.place(burek) shouldBe true
        map.isOccupied(Vector2d(0.0,0.0)) shouldBe false
        map.isOccupied(Vector2d(0.0,1.0)) shouldBe true
    }

    should("work well with more animals"){
        val map: BouncyMap = BouncyMap(2,2)
        var burki: ArrayList<Animal> = ArrayList<Animal>()
        for (i in 0 until 2){
            for (j in 0 until 2){
                var burek: Animal = Animal(position = Vector2d(i*1.0,j*1.0))
                burki.add(burek)
                map.place(burek) shouldBe true
            }
        }
        map.place(Animal()) shouldBe true
    }

    should("not be able to place animal on 0x0 map"){
        val map: BouncyMap = BouncyMap(0,0)
        map.place(Animal()) shouldBe false
    }
})