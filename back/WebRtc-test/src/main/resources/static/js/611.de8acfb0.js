(self["webpackChunkfront"]=self["webpackChunkfront"]||[]).push([[611],{4611:function(e,t,o){"use strict";o.r(t),o.d(t,{default:function(){return Ke}});var a=o(6252);const n=e=>((0,a.dD)("data-v-6e651b2e"),e=e(),(0,a.Cn)(),e),l={class:"planner-view d-flex flex-column align-items-center main"},i=n((()=>(0,a._)("div",{class:"spacer"},null,-1))),d=n((()=>(0,a._)("div",{class:"spacer"},null,-1))),s={class:"masonry-container drag-container",style:{width:"90%",display:"grid","column-gap":"10px","grid-auto-rows":"10px"}},r=["id"];function c(e,t,o,n,c,u){const m=(0,a.Q2)("b-modal");return(0,a.wg)(),(0,a.iD)("div",l,[i,d,(0,a._)("div",s,[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(n.widgetList,((e,t)=>((0,a.wg)(),(0,a.iD)("div",{class:"masonry-item drag-item",id:`dragItem-${t}-widget-${e}`,key:t},[(0,a.wy)(((0,a.wg)(),(0,a.j4)((0,a.LL)(n.componentList[e]),{class:"widget outer main"},null,512)),[[m,`modal-${n.componentNameList[e]}`]])],8,r)))),128))])])}var u=o(9963);const m={class:"widget calendar d-flex flex-column"},p={class:"widget-component"};function v(e,t,o,n,l,i){const d=(0,a.up)("v-date-picker");return(0,a.wg)(),(0,a.iD)("div",m,[(0,a._)("div",p,[(0,a.wy)((0,a._)("div",null,[(0,a.Wm)(d,{"is-expanded":"",mode:"date",modelValue:n.date,"onUpdate:modelValue":t[0]||(t[0]=e=>n.date=e)},null,8,["modelValue"])],512),[[u.F8,n.checkTheme()]]),(0,a.wy)((0,a._)("div",null,[(0,a.Wm)(d,{"is-expanded":"","is-dark":"",mode:"date",modelValue:n.date,"onUpdate:modelValue":t[1]||(t[1]=e=>n.date=e)},null,8,["modelValue"])],512),[[u.F8,!n.checkTheme()]])])])}var g=o(2262),f={setup(){let e=(0,g.iH)(new Date);function t(){return(0,g.iH)(JSON.parse(localStorage.getItem("theme"))).value}function o(e){let t=e.getMonth()+1,o=e.getDate();return t=t>=10?t:"0"+t,o=o>=10?o:"0"+o,e.getFullYear()+"-"+t+"-"+o}let a="",n=(0,g.iH)(null),l=(0,g.iH)([]);function i(){const e=this.$refs.form.checkValidity();return this.nameState.value=e,e}const d=()=>{a="",n.value=null},s=e=>{e.preventDefault(),this.handleSubmit()},r=()=>{this.checkFormValidity()&&(this.scheduleList.push(this.name),this.$nextTick((()=>{this.$bvModal.hide("modal-prevent-closing")})))};function c(){}function u(){}function m(){}function p(){}return{date:e,checkTheme:t,dateFormat:o,showTodo:c,addTodo:u,deleteTodo:m,cancel:p,checkFormValidity:i,resetModal:d,handleOk:s,handleSubmit:r,name:a,nameState:n,scheduleList:l}}},w=o(3744);const _=(0,w.Z)(f,[["render",v]]);var h=_,y=o(3577);const T=e=>((0,a.dD)("data-v-17f2aa02"),e=e(),(0,a.Cn)(),e),k={class:"d-flex align-items-center m-2",style:{overflow:"wrap"}},D=T((()=>(0,a._)("p",{class:"m-0 flex-grow-1",style:{"font-size":"1.5em"}},"오늘의 할 일",-1))),b=(0,a.Uk)("만들기"),x=T((()=>(0,a._)("hr",{class:"my-1 mb-3"},null,-1))),z={key:0,class:"ms-2"},C={class:"m-0 me-2 flex-grow-1"},L=(0,a.Uk)("시작"),S=(0,a.Uk)("완료"),U=(0,a.Uk)("삭제"),V={style:{color:"#FFFFFF"}},F=T((()=>(0,a._)("h5",{class:"mb-3 p-1"},"오늘의 할 일은?",-1))),H={for:"todo_time"},I=(0,a.Uk)("추가");function M(e,t,o,n,l,i){const d=(0,a.up)("b-button"),s=(0,a.up)("b-progress-bar"),r=(0,a.up)("b-progress"),c=(0,a.up)("b-card"),u=(0,a.up)("b-form-input"),m=(0,a.up)("b-modal");return(0,a.wg)(),(0,a.iD)("div",null,[(0,a.Wm)(c,{class:"p-1"},{default:(0,a.w5)((()=>[(0,a._)("div",k,[D,(0,a.Wm)(d,{size:"sm",onClick:n.showModal},{default:(0,a.w5)((()=>[b])),_:1},8,["onClick"])]),x,0==n.TODO_list.length?((0,a.wg)(),(0,a.iD)("p",z,"오늘의 할일이 없습니다. 추가해주세요.")):(0,a.kq)("",!0),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(n.TODO_list,((e,t)=>((0,a.wg)(),(0,a.iD)("div",{key:t,class:"d-flex flex-column mb-3"},[(0,a._)("div",{class:(0,y.C_)(["d-flex align-items-center",e.todoTime>0?"mb-2":""]),style:{overflow:"hidden"}},[(0,a._)("h5",C,(0,y.zw)(e.todoName),1),!e.flag&&e.todoTime>0?((0,a.wg)(),(0,a.j4)(d,{key:0,variant:"success",class:"m-0",size:"sm",onClick:t=>n.startTodo(e)},{default:(0,a.w5)((()=>[L])),_:2},1032,["onClick"])):(0,a.kq)("",!0),e.flag?((0,a.wg)(),(0,a.j4)(d,{key:1,class:"m-0",size:"sm",variant:"primary",onClick:t=>n.completeTodo(e)},{default:(0,a.w5)((()=>[S])),_:2},1032,["onClick"])):(0,a.kq)("",!0),e.todoTime<0?((0,a.wg)(),(0,a.j4)(d,{key:2,class:"m-0",variant:"danger",size:"sm",onClick:t=>n.eraseTodo(e)},{default:(0,a.w5)((()=>[U])),_:2},1032,["onClick"])):(0,a.kq)("",!0)],2),e.todoTime>0?((0,a.wg)(),(0,a.j4)(r,{key:0,animated:e.flag,striped:"",height:"1.2em",max:e.todoProgress,"show-value":""},{default:(0,a.w5)((()=>[(0,a.Wm)(s,{value:e.todoTime,variant:"success"},{default:(0,a.w5)((()=>[(0,a._)("span",null,[(0,a._)("strong",V,(0,y.zw)(Math.floor(e.todoTime/3600)>0?Math.floor(e.todoTime/3600)+"시 ":"")+" "+(0,y.zw)(Math.floor(e.todoTime%3600/60)>0?Math.floor(e.todoTime%3600/60)+"분 ":"")+" "+(0,y.zw)(Math.floor(e.todoTime%60)>0?Math.floor(e.todoTime%60)+"초":""),1)])])),_:2},1032,["value"])])),_:2},1032,["animated","max"])):(0,a.kq)("",!0)])))),128))])),_:1}),(0,a.Wm)(m,{modelValue:n.isModalShown,"onUpdate:modelValue":t[2]||(t[2]=e=>n.isModalShown=e),id:"modal-newroom",centered:"","ok-disabled":"","hide-header":"","hide-footer":""},{default:(0,a.w5)((()=>[(0,a.Wm)(c,{class:"mb-3"},{default:(0,a.w5)((()=>[F,(0,a.Wm)(u,{class:"mb-3",modelValue:n.todo_name,"onUpdate:modelValue":t[0]||(t[0]=e=>n.todo_name=e),placeholder:"제목",size:"lg"},null,8,["modelValue"]),(0,a._)("label",H,"할당 시간 : "+(0,y.zw)(n.todo_time)+"분",1),(0,a.Wm)(u,{id:"todo_time",modelValue:n.todo_time,"onUpdate:modelValue":t[1]||(t[1]=e=>n.todo_time=e),type:"range",min:"0",max:"180",step:"10"},null,8,["modelValue"])])),_:1}),(0,a.Wm)(d,{variant:"success",onClick:n.add_todo},{default:(0,a.w5)((()=>[I])),_:1},8,["onClick"])])),_:1},8,["modelValue"])])}var P=o(3907),N=o(8754),O="/todo",Z={createTodo:function(e){return console.log(e),new Promise(((t,o)=>{N.Z.post(O,e).then((e=>{"fail"===e.data?o("생성 중에 문제가 발생하였습니다"):t(e.data)})).catch((e=>{o(e)}))}))},getTodo:function(e){return new Promise(((t,o)=>{N.Z.get(O+"/"+e).then((e=>{"fail"===e.data?o("오늘의 할일을 불러오는 중 문제가 발생하였습니다"):t(e.data.todoList)})).catch((e=>{o(e)}))}))},updateTodo:function(e){return new Promise(((t,o)=>{N.Z.put(O,e).then((e=>{"success"===e.data?t("success"):o("TODO 목록 동기화 중 문제가 발생하였습니다")})).catch((e=>{o(e)}))}))},deleteTodo:function(e){return new Promise(((t,o)=>{N.Z["delete"](O+"/"+e).then((e=>{"success"===e.data?t("success"):o("TODO 삭제중 문제가 발생하였습니다")})).catch((e=>{o(e)}))}))}},E=o(6455),$=o.n(E),W={setup(){const e=(0,P.oR)();let t=(0,g.iH)([]);async function o(){try{t.value=await Z.getTodo(e.getters.getUserID),t.value.forEach((e=>{e["todoTime"]||(e["todoTime"]=e["todoProgress"]),e["flag"]||(e["flag"]=0==e["todoTime"])}))}catch(o){d(o)}}(0,a.wF)((()=>{o()}));let n=(0,g.iH)(!1);function l(){n.value=!0}function i(e){try{Z.updateTodo(e)}catch(t){d(t)}}function d(e){$().fire({icon:"warning",title:e,timer:2200})}let s=(0,g.iH)(""),r=(0,g.iH)(0);async function c(){let e={todoName:""==s.value?"제목 미지정":s.value,todoProgress:60*r.value,todoContent:"",todoEndTime:"",todoStartTime:""};try{let o=await Z.createTodo(e);e["todoNum"]=o,e["todoTime"]=e["todoProgress"],e["flag"]=0==e["todoTime"],t.value.push(e),s.value="",r.value=0,n.value=!1}catch(o){d(o)}}function u(e){e.flag=!0;let t=setInterval((()=>{e.todoTime-=1,e.todoTime<=0&&(clearInterval(t),$().fire({icon:"success",title:e.todoName+" 완료!"}))}),1e3)}async function m(e){e.flag=!1,e.todoTime=-1,e.todoProgress=-1;try{await Z.updateTodo(e)}catch(t){d(t)}}async function p(e){try{await Z.deleteTodo(e.todoNum);for(let o=0;o<t.value.length;o++)t.value[o].todoNum===e.todoNum&&t.value.splice(o,1)}catch(o){d(o)}}return{showModal:l,isModalShown:n,saveTodo:i,TODO_list:t,todo_name:s,todo_time:r,add_todo:c,startTodo:u,completeTodo:m,eraseTodo:p}}};const R=(0,w.Z)(W,[["render",M],["__scopeId","data-v-17f2aa02"]]);var q=R;const Y={class:"widget planner-todo"},j={class:"widget-component"},K=(0,a._)("div",{class:""},"대충 투두",-1),A={class:"d-flex"},B=(0,a.Uk)(" | "),J=(0,a.Uk)(" | "),Q=(0,a.Uk)(" | "),G=(0,a.Uk)(" | "),X=(0,a._)("p",{class:"my-4"},"대충 투두 디테일",-1);function ee(e,t,o,n,l,i){const d=(0,a.up)("b-modal");return(0,a.wg)(),(0,a.iD)("div",Y,[(0,a._)("div",j,[K,((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(n.todoList,(e=>((0,a.wg)(),(0,a.iD)("div",{key:e.todoNum},[(0,a._)("div",A,[(0,a._)("div",null,(0,y.zw)(e.todoProgress),1),B,(0,a._)("div",null,(0,y.zw)(e.todoName),1),J,(0,a._)("div",null,(0,y.zw)(e.todoContent),1),Q,(0,a._)("div",null,(0,y.zw)(e.todoStartTime.split("T")[0]),1),G,(0,a._)("div",null,(0,y.zw)(e.todoEndTime.split("T")[0]),1)])])))),128))]),(0,a.Wm)(d,{id:"modal-2",centered:"",title:"프로필"},{default:(0,a.w5)((()=>[X,((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(n.todoList,(e=>((0,a.wg)(),(0,a.iD)("div",{key:e.todoNum},[(0,a._)("div",null,(0,y.zw)(e.todoName),1)])))),128))])),_:1})])}var te={setup(){const e=(0,g.iH)();async function t(){}return(0,a.wF)((()=>{t()})),{todoList:e}}};const oe=(0,w.Z)(te,[["render",ee]]);var ae=oe;const ne=e=>((0,a.dD)("data-v-249304d0"),e=e(),(0,a.Cn)(),e),le={class:"widget planner-total"},ie={class:"widget-component",id:"1"},de=ne((()=>(0,a._)("div",{class:"widget-component-icon"},[(0,a._)("span",null,[(0,a._)("i",{class:"bi bi-book-half"})])],-1))),se={class:"widget-component-detail"},re=ne((()=>(0,a._)("div",null,"총 공부 시간",-1))),ce={style:{"font-size":"0.8em"}};function ue(e,t,o,n,l,i){return(0,a.wg)(),(0,a.iD)("div",le,[(0,a._)("div",ie,[de,(0,a._)("div",se,[re,(0,a._)("div",ce,(0,y.zw)(n.user_studytime),1)])])])}var me=o(5173),pe={setup(){(0,a.wF)((async()=>{let t=await me.Z.getProfile((0,P.oR)().getters.getUserID),o=t.profileTotalStudyTime;o||0==o?(e.value="",o>=60?(e.value+=Math.floor(o/60)+"시간 ",e.value+=o%60==0?"":o%60+"분"):e.value+=o+"분"):e.value="공부 시간 정보 불러오기 실패"}));let e=(0,g.iH)("시간 불러오는 중");return{user_studytime:e}}};const ve=(0,w.Z)(pe,[["render",ue],["__scopeId","data-v-249304d0"]]);var ge=ve;const fe=e=>((0,a.dD)("data-v-47a75f66"),e=e(),(0,a.Cn)(),e),we={class:"widget-component"},_e={class:"widget-component-icon"},he=["src"],ye={class:"widget-component-detail"},Te=fe((()=>(0,a._)("div",null,"공부 티어",-1))),ke={style:{"font-size":"0.8em"}};function De(e,t,n,l,i,d){return(0,a.wg)(),(0,a.iD)("div",null,[(0,a._)("div",we,[(0,a._)("div",_e,[(0,a._)("img",{src:o(8500)(`./${l.user_rank}.png`),style:{height:"1em",width:"0.8em"}},null,8,he)]),(0,a._)("div",ye,[Te,(0,a._)("div",ke,(0,y.zw)(l.user_rank),1)])])])}var be={setup(){(0,a.wF)((async()=>{let t=await me.Z.getProfile((0,P.oR)().getters.getUserID);t.profileRank?e.value=t.profileRank:e.value="브론즈"}));let e=(0,g.iH)("브론즈");return{user_rank:e,user_rank_color:(0,a.Fl)((()=>"마스터"===e.value?"#ff0062":"다이아"===e.value?"#00b4fc":"플레"===e.value?"#27e2a4":"골드"===e.value?"#ec9a00":"실버"===e.value?"#435f7a":"브론즈"===e.value?"#ad5600":void 0))}}};const xe=(0,w.Z)(be,[["render",De],["__scopeId","data-v-47a75f66"]]);var ze=xe;const Ce=e=>((0,a.dD)("data-v-66124704"),e=e(),(0,a.Cn)(),e),Le={class:"widget-component"},Se=Ce((()=>(0,a._)("div",{class:"widget-component-icon"},[(0,a._)("span",null,[(0,a._)("i",{class:"bi bi-trophy-fill"})])],-1))),Ue={class:"widget-component-detail"},Ve=Ce((()=>(0,a._)("div",null,"다음 티어까지",-1))),Fe={style:{"font-size":"0.8em"}};function He(e,t,o,n,l,i){return(0,a.wg)(),(0,a.iD)("div",null,[(0,a._)("div",Le,[Se,(0,a._)("div",Ue,[Ve,(0,a._)("div",Fe,(0,y.zw)(n.nextTear_restTime),1)])])])}var Ie={setup(){(0,a.wF)((async()=>{let t=await me.Z.getRestStudyTime((0,P.oR)().getters.getUserID);t||0==t?(e.value="",t>=60?(e.value+=Math.floor(t/60)+"시간 ",e.value+=t%60==0?"":t%60+"분"):e.value+=t+"분"):e.value="시간 정보 불러오기 실패"}));let e=(0,g.iH)("?");return{nextTear_restTime:e}}};const Me=(0,w.Z)(Ie,[["render",He],["__scopeId","data-v-66124704"]]);var Pe=Me;const Ne=e=>((0,a.dD)("data-v-7d1f9f7e"),e=e(),(0,a.Cn)(),e),Oe={class:"widget planner-timer"},Ze=Ne((()=>(0,a._)("div",{class:"widget-component"},[(0,a._)("div",{class:"timer-container"},[(0,a._)("div",{id:"timer",class:"timer"},[(0,a._)("div",{id:"lines"})])])],-1))),Ee=Ne((()=>(0,a._)("p",{class:"my-4"},"타이머",-1)));function $e(e,t,o,n,l,i){const d=(0,a.up)("b-modal");return(0,a.wg)(),(0,a.iD)("div",Oe,[Ze,(0,a.Wm)(d,{class:"widget-component-modal",id:"modal-planner-timer",centered:"",title:"타이머 세팅"},{default:(0,a.w5)((()=>[Ee])),_:1})])}var We={setup(){(0,a.bv)((()=>{const e=document.getElementById("lines");for(let t=0;t<30;t++){const o=document.createElement("div");o.classList.add("line"),o.style.transform=`rotate(${6*t}deg)`,t%5==0&&o.classList.add("thick"),e.append(o)}}))}};const Re=(0,w.Z)(We,[["render",$e],["__scopeId","data-v-7d1f9f7e"]]);var qe=Re,Ye={setup(){const e=(0,P.oR)(),t=(0,g.iH)([0,1,4,3,5,2]),o=[ge,ze,Pe,h,q,ae,qe],n=["planner-total","planner-rank","planner-status","planner-calendar","planner-progress","planner-todo","planner-timer"],l=(0,g.iH)({profileTotalStudyTime:null,profileRank:null,profilePlannerSet:null});async function i(){l.value=await me.Z.getProfile(e.getters.getUserID),t.value=await l.value.profilePlannerSet.split(","),(0,a.Y3)((()=>{d(),window.addEventListener("resize",d),s(document.querySelector(".drag-container"))}))}function d(){const e=document.querySelector(".masonry-container");if(!e)return;const t=getComputedStyle(e),o=parseInt(t.getPropertyValue("width"));e.style.gridTemplateColumns=o>1440?`repeat(4, calc(${o}px / 4)`:o>960?`repeat(3, calc(${o}px / 3)`:o>560?`repeat(2, calc(${o}px / 2)`:`repeat(1, ${o}px)`;const a=parseInt(t.getPropertyValue("column-gap")),n=parseInt(t.getPropertyValue("grid-auto-rows"));document.querySelectorAll(".masonry-item").forEach((e=>{e.style.gridRowEnd=`span ${Math.ceil(e.querySelector(".widget").scrollHeight/n+a/n)}`}))}function s(e){let o=null,n=null,l=null,i=null;function s(e){if(e.preventDefault(),e.target.closest(".widget")){const t=e.target.closest(".drag-item").id.split("-");l=t[3],i=t[1]}}function r(e){e.preventDefault(),e.target.style.visibility="visible",null!=l&&(t.value.splice(n,1),t.value.splice(i,0,o),(0,a.Y3)((()=>{d()})))}[...e.children].map((e=>{e.draggable=!0})),e.addEventListener("dragstart",(function(t){const a=t.target.closest(".drag-item").id.split("-");n=a[1],o=a[3],t.target.style.visibility="hidden",e.addEventListener("dragover",s,!1),e.addEventListener("dragend",r,!1)}))}return(0,a.bv)((()=>{i()})),{componentList:o,componentNameList:n,widgetList:t,profile:l,masonryLayout:d}}};const je=(0,w.Z)(Ye,[["render",c],["__scopeId","data-v-6e651b2e"]]);var Ke=je},8500:function(e,t,o){var a={"./골드.png":6471,"./다이아몬드.png":3508,"./마스터.png":5407,"./브론즈.png":5562,"./실버.png":1490,"./플래티넘.png":4126};function n(e){var t=l(e);return o(t)}function l(e){if(!o.o(a,e)){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}return a[e]}n.keys=function(){return Object.keys(a)},n.resolve=l,e.exports=n,n.id=8500},6471:function(e,t,o){"use strict";e.exports=o.p+"img/골드.c7850dfa.png"},3508:function(e,t,o){"use strict";e.exports=o.p+"img/다이아몬드.d8a12330.png"},5407:function(e,t,o){"use strict";e.exports=o.p+"img/마스터.988d125c.png"},5562:function(e,t,o){"use strict";e.exports=o.p+"img/브론즈.448525e2.png"},1490:function(e,t,o){"use strict";e.exports=o.p+"img/실버.45cc4aa5.png"},4126:function(e,t,o){"use strict";e.exports=o.p+"img/플래티넘.cdf44d14.png"}}]);
//# sourceMappingURL=611.de8acfb0.js.map