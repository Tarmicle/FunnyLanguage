{ makeCounter myCounter yourCounter e ->
  e = 10;
  makeCounter = {(balance) ->
    {(amount) ->
      balance = balance * amount * e}
    };
  e = 20;
  myCounter = makeCounter(100);
  yourCounter = makeCounter(200);
  println("MyCounter: ", myCounter(2));
  println("YourCounter: ", yourCounter(3));
}
