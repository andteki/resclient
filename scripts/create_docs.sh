#!/bin/bash

# Copyright (C) 2024 Sallai András
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

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


