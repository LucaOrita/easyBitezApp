mongo < ./js-dep/codes-drop.js
mongoimport --db easyDB --collection Codes --file ../../mongoImports/Codes.json --jsonArray
