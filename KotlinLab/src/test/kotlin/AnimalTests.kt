import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class AnimalTests : ShouldSpec({
    should("create Animal at right positions & with right directions"){
        val burek: Animal=Animal()
        val puszek: Animal=Animal(position = Vector2d(4.0,3.0), direction = MapDirection.EAST)
        burek.position shouldBe Vector2d(2.0,2.0)
        puszek.position shouldBe Vector2d(4.0,3.0)
        burek.direction shouldBe MapDirection.NORTH
        puszek.direction shouldBe MapDirection.EAST
    }

    should("move animals"){
        val burek: Animal=Animal()
        burek.move(MoveDirection.RIGHT)
        burek.direction shouldBe MapDirection.EAST
        burek.move(MoveDirection.FORWARD)
        burek.direction shouldBe MapDirection.EAST
        burek.position shouldBe Vector2d(3.0,2.0)
        burek.move(MoveDirection.LEFT)
        burek.move(MoveDirection.BACKWARD)
        burek.direction shouldBe MapDirection.NORTH
        burek.position shouldBe Vector2d(3.0,1.0)
        burek.move(MoveDirection.LEFT)
        burek.direction shouldBe MapDirection.WEST
        burek.isAt(Vector2d(3.0,1.0)) shouldBe true
    }
})