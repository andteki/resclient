#!/bin/bash

# Javadoc generálása
# mvn javadoc:javadoc

# Ellenőrzés, hogy sikerült-e a Javadoc generálása
if [ -d "target/site/apidocs" ]; then
    # Ha létezik a docs könyvtár, először töröljük, hogy friss verzió kerüljön bele
    rm -rf docs
    # Másolás a docs könyvtárba
    cp -r target/site/apidocs docs
    echo "A Javadoc sikeresen átmásolva a 'docs' könyvtárba."
else
    echo "Hiba: A Javadoc generálása nem sikerült vagy a 'target/site/apidocs' nem létezik."
fi


