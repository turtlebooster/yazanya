export const Setting = {
    state: {
        toggle: {
            modeToggle: localStorage.getItem('theme'),
            sidebarToggle: localStorage.getItem('sidebar'),
        }
    },
    getters: {
        getModeToggle: function(state) {
            return state.toggle.modeToggle;
        },
        getSidebarToggle: function(state) {
            return state.toggle.sidebarToggle;
        },
    },
    mutations: {
        setModeToggle: function(state) {
            if (state.toggle.modeToggle) {
                state.toggle.modeToggle = false;
            }
            else {
                state.toggle.modeToggle = true;
            }
        },
        setSidebarToggle: function(state) {
            // if (state.toggle.sidebarToggle == "fix") {
            //     state.toggle.sidebarToggle = "nofix"
            // }
            // else if (state.toggle.sidebarToggle == "nofix") {
            //     state.toggle.sidebarToggle = "fix"
            // }
            if (state.toggle.sidebarToggle) {
                state.toggle.sidebarToggle = false
            }
            else {
                state.toggle.sidebarToggle = true
            }
        },
    },
    actions: {
        setSidebarToggle: function(context) {
            context.commit('setSidebarToggle')
        },
        setModeToggle: function(context) {
            context.commit('setModeToggle')
        }
    },
}