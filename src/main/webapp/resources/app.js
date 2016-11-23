'use strict';


var App = angular.module('example', []);

App.controller('AuthController', function ($scope, $http) {
    $http.post('creative').success(function (data) {
            $scope.creatives = data;
    });
    $scope.reppassw="";
    $scope.messageVis=false;
    $scope.message="";
    $scope.noVisibility=false;
    $scope.authorize=function () {
        $http.post('user', $scope.user).success(function (data) {
            $scope.noVisibility=data;
            $scope.us=data;
        })
    };
    $scope.addUser=function () {
        if($scope.reppassw===$scope.user.password) {
            $http.post('reg', $scope.user).success(function (data) {
                if(data) {
                    $scope.message = "You has been successfully reg!";
                    $scope.messageVis=true;
                }else{
                    $scope.message="This user does already exists!";
                }
            })
        }else{
            $scope.message="Your passwords not equals!";
        }
    };
    $scope.deleteUser=function(){
      $http.remove('user', $scope.user).success(function (data) {
          $scope.users=data;
      })
    };
    $scope.authorizeTW=function () {
      $http.post("twitter").success(function (data) {
          window.location=data;
      })
    };
});