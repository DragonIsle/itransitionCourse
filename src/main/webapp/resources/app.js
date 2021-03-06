'use strict';


var App = angular.module('example', []);

App.controller('AuthController', function ($scope, $http) {
    $scope.style="css/cyborg.css";
    $http.get("session/style").success(function (data) {
      $scope.style=data;
    });
    $http.post('creative').success(function (data) {
            $scope.creatives = data;
    });
    $http.post('session').success(function (data) {
        $scope.us=data;
        $scope.noVisibility=data;
    });
    $http.get('creative/tag/all').success(function (data) {
        $scope.tags=data;
    });
    $scope.reppassw="";
    $scope.messageVis=false;
    $scope.noVisibility=false;
    $scope.authorize=function () {
        $http.post('user', $scope.user).success(function(data) {
            if(data) {
                $scope.noVisibility = data;
                $scope.us = data;
                $scope.message=false;
                $scope.style=data.theme;
            }else{
                $scope.message=true;
            }
        })
    };
    $scope.sortForName=function () {
        $scope.creatives.sort(function (a, b) {
            if(a.name < b.name) return -1;
            if(a.name > b.name) return 1;
            return 0;
        })
    };
    $scope.sortForRating=function () {
        $scope.creatives.sort(function (a, b) {
            return b.rating-a.rating;
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
                    $scope.messageVis=true;
                }
            })
        }else{
            $scope.message="Your passwords not equals!";
            $scope.messageVis=true;
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
    $scope.authorizeFB=function () {
        $http.post("facebook").success(function (data) {
            window.location=data;
        })
    };
    $scope.authorizeVK=function () {
        $http.post("vk").success(function (data) {
            window.location=data;
        })
    };
    $scope.personalPage=function () {
      window.location="personalPage.html";
    };
    $scope.redirect=function (creative) {
        window.location="creativeWatch.html?id="+creative.id;
    };
    $scope.logout=function () {
        $http.get("session").success(function (data) {
            window.location="index.html";
        })
    };
    $scope.search=function (tag) {
        window.location="searchResult.html?name="+tag.name;
    }
});