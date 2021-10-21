#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {

    def script 

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName){
        script.echo 'building the image....'
        script.withCredentials([script.usernamePassword(credentialsId: 'nexus-login-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
            script.sh "docker build -t $imageName ."
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin 64.227.176.229:8083"
            script.sh "docker push $imageName"
        }
    }

}