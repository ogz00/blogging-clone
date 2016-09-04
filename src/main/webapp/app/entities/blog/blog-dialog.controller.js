(function() {
    'use strict';

    angular
        .module('bloggingApp')
        .controller('BlogDialogController', BlogDialogController);

    BlogDialogController.$inject = ['Principal', '$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Blog', 'Entry', 'User'];

    function BlogDialogController (Principal, $timeout, $scope, $stateParams, $uibModalInstance, entity, Blog, Entry, User) {
        var vm = this;

        vm.blog = entity;
        vm.clear = clear;
        vm.save = save;
        vm.entries = Entry.query();
        vm.users = User.query({}, function () {
            userHandling();
        });
        vm.currentAccount = null;

        function userHandling() {
            Principal.identity().then(function(account) {
                vm.currentAccount = account;
                console.log(vm.currentAccount);
                for(var i = 0; i< vm.users.length; i++)
                {
                    if (vm.currentAccount.login == vm.users[i].login) {
                        vm.users.splice(i,1);
                    }
                };
            });
        }



        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.blog.id !== null) {
                Blog.update(vm.blog, onSaveSuccess, onSaveError);
            } else {
                Blog.save(vm.blog, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('bloggingApp:blogUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
