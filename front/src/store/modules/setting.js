export const Setting = {
    state: {
        toggle: {
            sidebarToggle: localStorage.getItem('sidebar'),
            modeToggle: localStorage.getItem('theme')
        }
    },
    getters: {
        getSidebarToggle: function(state) {
            return state.toggle.sidebarToggle;
        },
        getModeToggle: function(state) {
            return state.toggle.modeToggle;
        }
    },
    mutations: {
        setSidebarToggle: function(state) {
            if (state.toggle.sidebarToggle == "fix") {
                state.toggle.sidebarToggle = "nofix"
            }
            else if (state.toggle.sidebarToggle == "nofix") {
                state.toggle.sidebarToggle = "fix"
            }
        },
        setModeToggle: function(state) {
            if (state.toggle.sidebarToggle) {
                state.toggle.sidebarToggle = false;
            }
            else {
                state.toggle.sidebarToggle = true;
            }
        }
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