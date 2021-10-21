#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {

    def script 

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName){
        script.echo 'building the image....'
        script.sh "docker build -t $imageName ."
    }

    def dockerLogin(){
        script.withCredentials([script.usernamePassword(credentialsId: 'nexus-login-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
            script.sh 'echo $PASS | docker login -u $USER --password-stdin 64.227.176.229:8083'
            script.sh "docker push $imageName"
        }
    }

    def dockerPush(String imageName){
        script.sh "docker push $imageName"
    }

}