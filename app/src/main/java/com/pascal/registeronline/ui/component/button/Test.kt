package com.pascal.registeronline.ui.component.button

fun main() {
    test(5)
}

fun test(num: Int) {
    for (i in 1..num) {
        for (i in 1..i) {
            print("*")
        }
        println("")
    }
}