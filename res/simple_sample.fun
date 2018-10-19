
{ ->
 print("Hello, world!\n");
 print("Hi!", "\n");
 println("你好");
}


{_true, _false, _if ->
    _true = {(t, f) -> t};
    _false = {(t, f) -> f};
    _if = {(c, t, e) -> c(t, e)()};

    println(_if(_false, {-> while true do {} od}, {-> "False"}))
}
