#! /bin/bash

echo "======[START SETUP EASY SERVER]======"
echo "--> Setup environment"

#set up environment
apt update #update environment
echo -ne "#####                 (24%)\r"
apt install default-jre #install java runtime
echo -ne "##########            (48%)\r"
apt install default-jdk #install java development kit
echo -ne "###############       (75%)\r"
apt install maven #install maven repository for spring
echo -ne "####################  (94%)\r"

#set up Firewall
echo "--> Setup Firewall"
ufw reset
ufw status #see Firewall status
ufw enable #enable Firewall
ufw status #see if Firewall is enabled
ufw allow ssh #allow ssh into droplet
ufw allow 22 #allow ssh port forwarding
ufw allow http #allow traffic to enter
ufw allow http/tcp #alow traffic to send data
ufw allow https
ufw allow https/tcp
ufw allow 443
ufw allow 8443
echo -ne "###################### (100%)\r"
ufw status #see status

echo "====[END SETUP EASY SERVER]===="
