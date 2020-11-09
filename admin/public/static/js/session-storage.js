SESSION_KEY_COURSE = "SESSION_KEY_COURSE"; //课程管理页面点击大章管理时，保存课程信息
SESSION_KEY_CHAPTER = "SESSION_KEY_CHAPTER" //大章管理页面点击小节管理时，保存小节信息
SessionStorage = {
    get: function (key) {
        let v = sessionStorage.getItem(key);
        if (v && typeof(v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    set: function (key, data) {
        sessionStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        sessionStorage.removeItem(key);
    },
    clearAll: function () {
        sessionStorage.clear();
    }
};