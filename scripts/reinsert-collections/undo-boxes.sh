mongo < ./js-dep/boxes-drop.js
mongoimport --db easyDB --collection boxes --file ../../mongoImports/boxes.json --jsonArray
