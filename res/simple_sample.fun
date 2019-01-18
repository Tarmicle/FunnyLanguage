{ result ->
    result = 2;
    if result == 0 && result == 2 then println("Impossibile") else println("Ovvio") fi;
    result += 10;
    println(result);
    result = 0;
    println(result);
    whilenot result == 10 do
        println("Ciclo");
        result = 10
    od;
}