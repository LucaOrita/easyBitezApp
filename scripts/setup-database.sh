echo "--> Setup MongoDB"
echo -ne '                     (0%)\r'
wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list
sudo apt-get update
sudo apt-get install -y mongodb-org
echo -ne '################     (70%)\r'
sudo systemctl start mongod
sudo systemctl status mongod


echo -e "--> Populating Data\r"
mongoimport --db easyDB --collection recipes --file ../mongoImports/recipes.json --jsonArray
echo -ne '#################    (75%)\r'
mongoimport --db easyDB --collection users --file ../mongoImports/users.json --jsonArray
echo -ne '##################   (80%)\r'
mongoimport --db easyDB --collection boxes --file ../mongoImports/boxes.json --jsonArray
echo -ne '##################   (90%)\r'
mongoimport --db easyDB --collection Codes --file ../mongoImports/Codes.json --jsonArray
echo -ne '#################### (100%)\r'
echo -ne "\r"
echo -n "Added Data"