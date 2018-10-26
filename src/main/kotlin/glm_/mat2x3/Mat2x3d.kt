package  glm_.mat2x3

import glm_.BYTES
import glm_.d
import glm_.set
import glm_.vec3.Vec3d
import glm_.vec3.Vec3t
import kool.doubleBufferBig
import kool.pos
import org.lwjgl.system.MemoryStack
import java.nio.ByteBuffer
import java.nio.DoubleBuffer
import java.util.*

/**
 * Created by GBarbieri on 09.12.2016.
 */

class Mat2x3d(dummy: Int, var array: DoubleArray) : Mat2x3t<Double>() {

    // -- Accesses --

    override operator fun get(index: Int) = Vec3d(index * 3, array)
    override operator fun get(column: Int, row: Int) = array[column * 3 + row]

    override operator fun set(column: Int, row: Int, value: Double) = array.set(column * 3 + row, value)

    override operator fun set(index: Int, value: Vec3t<out Number>) {
        array[index * 3] = value.x.d
        array[index * 3 + 1] = value.y.d
        array[index * 3 + 2] = value.z.d
    }

    operator fun set(i: Int, v: Vec3d) {
        v.to(array, i * 3)
    }


    fun toDoubleArray(): DoubleArray = to(DoubleArray(length), 0)
    infix fun to(doubles: DoubleArray): DoubleArray = to(doubles, 0)
    fun to(doubles: DoubleArray, index: Int): DoubleArray {
        System.arraycopy(array, 0, doubles, index, length)
        return doubles
    }

    override fun to(buf: ByteBuffer, offset: Int): ByteBuffer {
        return buf
                .putDouble(offset + 0 * Double.BYTES, array[0])
                .putDouble(offset + 1 * Double.BYTES, array[1])
                .putDouble(offset + 2 * Double.BYTES, array[2])
                .putDouble(offset + 3 * Double.BYTES, array[3])
                .putDouble(offset + 4 * Double.BYTES, array[4])
                .putDouble(offset + 5 * Double.BYTES, array[5])
    }


    fun toDoubleBufferStack(): DoubleBuffer = to(MemoryStack.stackGet().mallocDouble(length), 0)
    infix fun toDoubleBuffer(stack: MemoryStack): DoubleBuffer = to(stack.mallocDouble(length), 0)
    fun toDoubleBuffer(): DoubleBuffer = to(doubleBufferBig(length), 0)
    infix fun to(buf: DoubleBuffer): DoubleBuffer = to(buf, buf.pos)

    fun to(buf: DoubleBuffer, offset: Int): DoubleBuffer {
        buf[offset + 0] = array[0]
        buf[offset + 1] = array[1]
        buf[offset + 2] = array[2]
        buf[offset + 3] = array[3]
        buf[offset + 4] = array[4]
        buf[offset + 5] = array[5]
        return buf
    }

    override var a0: Double
        get() = array[0]
        set(v) = array.set(0, v)
    override var a1: Double
        get() = array[1]
        set(v) = array.set(1, v)
    override var a2: Double
        get() = array[2]
        set(v) = array.set(2, v)

    override var b0: Double
        get() = array[4]
        set(v) = array.set(4, v)
    override var b1: Double
        get() = array[5]
        set(v) = array.set(5, v)
    override var b2: Double
        get() = array[6]
        set(v) = array.set(6, v)


    companion object {
        const val length = Mat2x3t.length
        @JvmField
        val size = length * Double.BYTES
    }

    override fun size() = size

    override fun equals(other: Any?) = other is Mat2x3d && Arrays.equals(array, other.array)

    override fun hashCode() = 31 * this[0].hashCode() + this[1].hashCode()
}