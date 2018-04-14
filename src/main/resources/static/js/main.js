$(function () {
    var self = {
        cars: [],
        isResultViewVisible: false,

        init: function () {
            self.cacheDom();
            self.bindEvents();
            self.renderAll();
        },
        cacheDom: function () {
            self.$txtLaps = $('#txt-laps');
            self.$txtCars = $('#txt-cars');
            self.$btnStart = $('#btn-start');
            self.$divCarResult = $('.div-car-result');
            self.$tbodyCarResult = $('.tbody-car-result');
            self.templateCarResult = $('#template-car-result').html();
        },
        bindEvents: function () {
            self.$btnStart.on('click', self.onStartBtnClicked.bind(this));
        },
        renderAll: function () {
            self.renderCarResult();
            self.renderResultView();
        },
        renderCarResult: function () {
            self.$tbodyCarResult.empty();

            var html = Mustache.render(self.templateCarResult, {cars: self.cars});
            self.$tbodyCarResult.append(html);
        },
        renderResultView: function () {
            if(self.isResultViewVisible) {
                self.$divCarResult.show();
            } else {
                self.$divCarResult.hide();
            }
        },

        onStartBtnClicked: function () {
            self.quickStart(
                self.$txtLaps.val(),
                self.$txtCars.val(),
                function (cars) { // onSuccess
                    self.cars = cars;
                    self.isResultViewVisible = true;
                    console.log(self.cars);

                    self.renderResultView();
                    self.renderCarResult();
                },
                function (error) { // onError
                    console.log(error);
                    alert("시작 실패");
                });
        },

        /**
         * 빠른 시작 함수
         */
        quickStart: function (laps, cars, success, error) {
            $.ajax({
                type: 'POST',
                url: '/cars/quick-start',
                contentType: 'application/json',
                data: JSON.stringify({
                    laps: laps,
                    carNames: cars
                }),
                success: success,
                error: error
            });
        }
    };

    self.init();
});
