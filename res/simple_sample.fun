{a b c fn _true ->
    a = 1023.8;
   /* b = 0;
    a = 1023.8;
    c = 10;
    b = a + 1;
    c = b + c + c + 15.2;*/
    fn = {(n) a -> a = 1 + n};
    println(fn(2));
    //b = 0;
    println(fn(4));
    println(a);
    //println(c);
}