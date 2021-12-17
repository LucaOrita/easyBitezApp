mongo < ./js-dep/recipes-drop.js
mongoimport --db easyDB --collection recipes --file ../../mongoImports/recipes.json --jsonArray
