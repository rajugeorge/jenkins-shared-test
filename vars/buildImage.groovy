#!/usr/bin/env groovy

def call(String imageName){
    echo 'building the image....'
    withCredentials([usernamePassword(credentialsId: 'nexus-login-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh "docker build -t $imageName ."
        sh 'echo $PASS | docker login -u $USER --password-stdin 64.227.176.229:8083'
        sh "docker push $imageName"
    }
}