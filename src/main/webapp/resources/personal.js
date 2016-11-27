/**
 * Created by saul on 11/22/16.
 */
'use strict';

var personal = angular.module('personal', []);

personal.controller('PageController', function ($scope, $http) {
    $http.post('session').success(function (data) {
        $scope.user = data;
        $scope.isAdmin=($scope.user.role!="USER");
        $http.get('user/achievements', {params:{login: data.login}}).success(function (data) {
            $scope.achievements=data;
        })
    });
    $http({
            url: 'creative',
            method: "GET"
        }
    ).success(function (data) {
        $scope.creatives=data;
    });
    $scope.addCreative=function () {
        $http.put("user", $scope.user).success(function (data) {
            $scope.creatives=data;
        })
    };
    $scope.getCreator=function (creative) {
        $http.post('creative/creator', {params:{creativeId: creative.id}}).success(function (data) {
            creative.creator=data;
        })
    };
    $scope.remove=function (creative) {
        $http.delete('creative', {params: {creativeId: creative.id}}).success(function () {
            $http({
                    url: 'creative',
                    method: "GET"
                }
            ).success(function (data) {
                $scope.creatives=data;
            });
        })
    };
    $scope.redact=function (creative) {
        window.location="creativePage.html?id="+creative.id;
    };
    $scope.logout=function () {
        $http.get("session").success(function (data) {
            window.location="index.html";
        })
    }
});

personal.controller('CreativeController', function ($scope, $http) {
    function getId() {
        return window.location.search.substring(1).split("=")[1];
    }
    $http.get('session/approve', {params: {creativeId: getId()}}).success(function (data) {
        $scope.user = data;
    });
    $http.get("creative/single",{params: {creativeId: getId()}}).success(function (data) {
       $scope.cr=data;
        $http.get("creative/tag", {params: {creativeId: getId()}}).success(function (data) {
            $scope.tags=data;
            $http.get("creative/chapter", {params: {creativeId: getId()}}).success(function (data) {
                $scope.chapters=data;
            });
        });
    });
    $scope.addChapter=function () {
       $http.get("creative/add", {params: {creativeId: getId()}}).success(function (data) {
           $scope.chapters=data;
       })
    };
    $scope.remove=function (chapter) {
        $http.delete("creative/chapter/remove", {params: {chapterId: chapter.id, creativeId: getId()}}).success(function (data) {
            $scope.chapters=data;
        })
    };
    $scope.addTag=function () {
        $http.post('creative/tag/add', $scope.currentTag, {params: {creativeId: getId()}}).success(function (data) {
            $scope.tags=data;
            $scope.currentTag.name="";
        })
    };
    $scope.save=function (chapter) {
        $http.post('creative/chapter/save', chapter);
    };
    $scope.clear=function (chapter) {
        chapter.text="";
    };
    $scope.saveName=function () {
        $http.get("creative/name/save", {params: {name: $scope.cr.name, creativeId: getId()}}).success(function (data) {
            $scope.cr=data;
        })
    };
    $scope.removeTag=function (tag) {
        $http.post("creative/tag/remove", tag, {params:{creativeId: $scope.cr.id}}).success(function (data) {
            $scope.tags=data;
        })
    };
    $scope.search=function (tag) {
        window.location="searchResult.html?name="+tag.name;
    }
});