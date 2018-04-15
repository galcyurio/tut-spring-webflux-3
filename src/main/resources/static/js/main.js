$(function () {
    var self = {
        game: {},
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

            self.$spanId = $('.span-id');
            self.$spanLaps = $('.span-laps');

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

            var html = Mustache.render(self.templateCarResult, self.game);
            self.$tbodyCarResult.append(html);
        },
        renderResultView: function () {
            if (self.isResultViewVisible) {
                self.$spanId.html(self.game.id);
                self.$spanLaps.html(self.game.laps);
                self.$divCarResult.show();
            } else {
                self.$divCarResult.hide();
                self.$spanId.html();
                self.$spanLaps.html();
            }
        },

        /**
         * 시작 버튼 눌림
         */
        onStartBtnClicked: function () {
            self.createGame();
        },

        /**
         * 게임 만들기
         */
        createGame: function() {
            $.ajax({
                type: 'POST',
                url: '/cars',
                contentType: 'application/json',
                data: JSON.stringify({
                    laps: parseInt(self.$txtLaps.val()),
                    carNames: self.$txtCars.val()
                }),
                success: self.onCreateGameSuccess,
                error: self.onCreateGameError
            });
        },

        /**
         * 게임 만들기 성공
         */
        onCreateGameSuccess: function(game) {
            console.log("onCreateGameSuccess", game);
            self.game = game;

            // self.quickStart(game.id);
            self.streamStart(game.id);
        },

        /**
         * 게임 만들기 에러
         */
        onCreateGameError: function(error) {
            console.log("onCreateGameError", error);
            alert("게임 만들기 실패");
        },

        /**
         * 빠른 시작
         */
        quickStart: function (id) {
            $.ajax({
                type: 'POST',
                url: '/cars/' + id + '/quick-start',
                contentType: 'application/json',
                success: self.onQuickStartSuccess,
                error: self.onQuickStartError
            });
        },

        /**
         * 빠른 시작 성공
         */
        onQuickStartSuccess: function (cars) {
            self.game.cars = cars;
            self.isResultViewVisible = true;
            console.log("onQuickStartSuccess", self.game);

            self.renderAll();
        },

        /**
         * 빠른 시작 에러
         */
        onQuickStartError: function (error) {
            console.log("onQuickStartError", error);
            alert("시작 실패");
        },

        /**
         * `EventStream`을 통한 시작
         */
        streamStart: function (id) {
            var eventSource = new EventSource('/cars/' + id + '/stream-start');
            eventSource.onmessage = self.onStreamStartMessage
        },

        /**
         * `EventStream` 을 통해 메세지 받음
         */
        onStreamStartMessage: function (event) {
            self.game = JSON.parse(event.data);
            self.isResultViewVisible = true;
            console.log("streamStart", self.game);

            self.renderAll();
        }
    };

    self.init();
});
