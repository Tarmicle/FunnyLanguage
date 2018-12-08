{makeCounter myCounter yourCounter n ->

    makeCounter = {(balance) ->
        {(amount) -> balance = balance + amount}
    };

    myCounter = makeCounter(100);
    yourCounter = makeCounter(50);

    println("myCounter: ", myCounter(0));
    println("yourCounter: ", yourCounter(0));

    n = 0;
    while n < 10 do
        println("myCounter[", n, "]: ", myCounter(50));
        println("yourCounter[", n, "]: ", yourCounter(10));
        n = n + 1
    od
}
