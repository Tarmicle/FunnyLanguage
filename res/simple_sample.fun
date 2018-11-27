/*
{ ->
	print("Hello, world!\n");
	print("Hi!", "\n");
	println("你好");
}
*/



{a ->
    a = 1024;
    println(a)
}
/*

{_true _false _if ->
    _true = {(t f) -> t};
    _false = {(t f) -> f};
    _if = {(c t e) -> c(t, e)()};

    println(_if(_true, {-> while true do {} od}, {-> "False"}))
}

{average, sqr, abs, sqrt, x ->
    average = {(x, y) -> (x + y) / 2};
    sqr = {(x) -> x * x};
    abs = {(x) -> if x >= 0 then x else -x fi};
    sqrt = {(x) tolerance, isGoodEnough, improve, sqrtIter ->
        tolerance = 1e-30;
*/