"use strict";(self["webpackChunkfront"]=self["webpackChunkfront"]||[]).push([[465],{2745:function(e,o,a){var l=a(8164),t=a(9669),s=a.n(t);o["Z"]={addThumbnail:function(e,o){return new Promise(((a,t)=>{s().post("https://i7b310.p.ssafy.io:8081/B310/room/Thumbnail/"+e,o,{headers:{"Content-Type":"multipart/form-data","access-token":l.ZP.getAccessToken()}}).then((e=>{"success"===e.data?a(!0):t("방 섬네일 업로드 중 문제가 발생하였습니다")})).catch((e=>{t(e)}))}))},addProfileImage:function(e,o){return new Promise(((a,t)=>{s().post("https://i7b310.p.ssafy.io:8081/B310/user/profile/"+e,o,{headers:{"Content-Type":"multipart/form-data","access-token":l.ZP.getAccessToken()}}).then((e=>{"success"===e.data?a(!0):t("프로필 사진 설정 중 문제가 발생하였습니다")})).catch((e=>{t(e)}))}))}}},2895:function(e,o,a){var l=a(8754),t="/room";o["Z"]={getRoomList:function(e,o,a){let s={video:e,sound:o,fullcheck:a};return new Promise(((e,o)=>{l.Z.post(t+"/filter",s).then((o=>{e(o)})).catch((e=>{o(e)}))}))},getRoomInfo:function(e){return new Promise(((o,a)=>{l.Z.get(t+"/"+e).then((e=>{"success"===e.data.message?o(e.data.room):a("방 정보를 서버로부터 가져오는데 실패하였습니다")})).catch((e=>{a(e)}))}))},joinRoom:function(e,o=""){let a={roomPw:o};return new Promise(((o,s)=>{l.Z.post(t+"/"+e,a).then((e=>{switch(e.data){case"success":o(!0);break;case"failToForcedExitUser":s("강제 퇴장당한 방에는 입장 할 수 없습니다");break;case"failToFullRoom":s("방 인원이 가득찼습니다");break;case"failToPw":s("비밀번호가 틀렸습니다");break;case"alreadyParticipateUser":s("이미 방에 입장중입니다");break}})).catch((e=>{s(e)}))}))},leaveRoom:function(e,o){let a={userId:o};return new Promise((o=>{l.Z.patch(t+"/exit/"+e,a).then((e=>{o(e)}))}))},getRoomRecommendList:function(e){return new Promise(((o,a)=>{l.Z.get(t+"/recommend?hashtagName="+e).then((e=>{o(e.data.roomList)})).catch((e=>{a(e)}))}))},getRoomHistoryList:function(){return new Promise(((e,o)=>{l.Z.get(t+"/history").then((o=>{e(o.data.roomList)})).catch((e=>{o(e)}))}))},creatRoom:function(e){return new Promise(((o,a)=>{l.Z.post(t,e).then((e=>{"fail"===e.data?a("방 생성 중 문제가 발생하였습니다"):o(e.data)})).catch((e=>{a(e)}))}))},hasRoomPw:function(e){return new Promise(((o,a)=>{l.Z.get(t+"/hasPw/"+e).then((e=>{"fail"===e.data&&a("존재하지 않는 방입니다"),o(e.data)})).catch((e=>{a(e)}))}))},removeRoom:function(e){return new Promise(((o,a)=>{l.Z["delete"](t+"/"+e).then((e=>{"fail"===e.data&&a("방 삭제 실패"),o(e.data)})).catch((e=>{a(e)}))}))},kickUser:function(e,o){let a={userNickname:e,roomNum:o+""};return new Promise(((e,o)=>{l.Z.post(t+"/forceExit",a).then((a=>{"success"===a.data?e(!0):o("강퇴 중 서버연결에 문제가 생겼습니다.")})).catch((e=>{o(e)}))}))},addHashTags:function(e,o){return new Promise(((a,s)=>{l.Z.post(t+"/hashtag?roomNum="+e,o).then((e=>{"success"===e.data?a(!0):s("방의 해쉬태그 추가 중 문제가 발생하였습니다")})).catch((e=>{s(e)}))}))},getParticipants:function(e){return new Promise(((o,a)=>{l.Z.get(t+"/join/"+e).then((e=>{0==e.data.length?a("참여자 정보 불러오기 실패"):o(e.data)})).catch((e=>{a(e)}))}))},searchRoomWithName:function(e){return new Promise(((o,a)=>{l.Z.get(t+"/searchByName/"+e.trim()).then((e=>{o(e.data.roomList)})).catch((e=>{a(e)}))}))},searchRoomWithTags:function(e){return new Promise(((o,a)=>{l.Z.get(t+"/searchByTags?hashtagName="+e).then((e=>{o(e.data.roomList)})).catch((e=>{a(e)}))}))}}},7608:function(e,o,a){a.r(o),a.d(o,{default:function(){return Qe}});var l=a(6252),t=a(3577),s=a(9963);const i={class:"d-flex flex-column align-items-center",style:{padding:"8px"}},r=(0,l._)("div",{class:"spacer"},null,-1),m={class:"search main-color outer d-flex align-items-center",style:{height:"3.3em"}},n=(0,l._)("i",{class:"bi bi-search"},null,-1),c=[n],d=(0,l._)("div",{class:"spacer"},null,-1),u=(0,l._)("div",{class:"spacer"},null,-1),w={key:0},h={key:1,style:{width:"95%","font-size":"28px","font-weight":"bold"}},g={key:2},f=(0,l._)("h3",null,"일치하는 방이 없습니다.",-1),p=[f],v=(0,l._)("div",{class:"spacer"},null,-1),y={key:3,class:"container-fluid",style:{"border-radius":"24px",width:"95%"}},k={class:"row"},b={key:4},_=(0,l._)("h4",{class:"m-0",style:{"vertical-align":"middle"}},"더 보기",-1),R={key:5,class:"spacer"},H={key:6,class:"liner"},x={key:7,class:"spacer"},T={key:8,class:"spacer"},P=(0,l._)("div",{style:{width:"95%","font-size":"28px","font-weight":"bold"}}," 이전 참여방 ",-1),V=(0,l._)("div",{class:"spacer"},null,-1),N={class:"container-fluid",style:{"border-radius":"24px",width:"95%"}},D={class:"row"},W={class:"room col-12 col-sm-6 col-md-4 col-lg-2"},S=(0,l._)("div",{class:"room-component d-flex flex-column"},[(0,l._)("i",{class:"bi bi-plus-circle-fill",style:{"font-size":"2em",position:"absolute",left:"50%",top:"50%",transform:"translate(-50%, -50%)"}})],-1),U=[S],Z={key:9},q=(0,l._)("h4",{class:"m-0",style:{"vertical-align":"middle"}},"더 보기",-1),C=(0,l.uE)('<div class="spacer"></div><div class="liner"></div><div class="spacer"></div><div class="spacer"></div><div style="width:95%;font-size:28px;font-weight:bold;"> 추천하는 공부방 </div><div class="spacer"></div>',6),z={key:10,class:"container-fluid",style:{"border-radius":"24px",width:"95%"}},F={class:"row"},j={key:11,class:"mb-5"},L=(0,l._)("h3",null,"현재 관심 태그가 등록되어 있지 않습니다. 내 프로필에서 추가해주세요.",-1),M=[L],A={key:12,class:"mb-5"},I=(0,l._)("h4",{class:"m-0",style:{"vertical-align":"middle"}},"더 보기",-1),K={style:{"font-family":"'Noto Sans KR', sans-serif"}},B=(0,l.Uk)(" 방 이름은 1-20글자여야 해요 "),E=(0,l._)("small",null,[(0,l._)("i",{class:"bi bi-lock-fill"}),(0,l.Uk)(" 비밀방")],-1),$=(0,l.Uk)(" 비밀번호는 최소 4글자의 영문/숫자여야 해요 "),Y={class:"d-flex mt-3 align-items-center"},O={class:"flex-grow-1"},Q=(0,l.Uk)(" 방 태그는 최대 3개까지 지정할 수 있어요 "),G=(0,l._)("i",{class:"bi bi-question-circle ms-2",style:{"vertical-align":"middle"}},null,-1),J=(0,l.Uk)(" 방 추천 및 검색 시 사용돼요 "),X={class:"d-flex mt-3",style:{}},ee=(0,l._)("small",{class:"me-4"},[(0,l._)("i",{class:"bi bi-mic-mute-fill"}),(0,l.Uk)(" 마이크 끄기")],-1),oe=(0,l._)("small",null,[(0,l._)("i",{class:"bi bi-camera-video-off-fill"}),(0,l.Uk)("  캠화면 끄기")],-1),ae=(0,l.Uk)("방 세부 설정"),le={for:"range-1"},te={class:"d-flex"},se=(0,l._)("small",null,[(0,l._)("i",{class:"bi bi-alarm-fill"}),(0,l.Uk)(" 공부 알람 사용하기")],-1),ie=(0,l._)("i",{class:"bi bi-question-circle ms-2",style:{"vertical-align":"middle"}},null,-1),re=(0,l.Uk)(" 지정된 시간 마다 알람을 틀어줘요 "),me={key:0},ne={class:"mt-2",for:"range-1"},ce={class:"mt-2",for:"range-1"},de=(0,l._)("label",{class:"mt-3",for:"formFile"},"방 썸네일 사진",-1),ue=(0,l._)("input",{class:"form-control",type:"file",id:"roomThumbnail",accept:"image/png, image/jpeg"},null,-1),we={class:"d-flex mt-2"},he=(0,l.Uk)("완료"),ge=(0,l._)("div",{class:"flex-grow-1"},null,-1),fe=(0,l.Uk)("닫기");function pe(e,o,a,n,f,S){const L=(0,l.up)("b-spinner"),pe=(0,l.up)("MainRoomComp"),ve=(0,l.up)("b-button"),ye=(0,l.up)("b-form-input"),ke=(0,l.up)("b-form-invalid-feedback"),be=(0,l.up)("b-form-checkbox"),_e=(0,l.up)("b-form-textarea"),Re=(0,l.up)("b-form-tags"),He=(0,l.up)("Popper"),xe=(0,l.up)("b-card"),Te=(0,l.up)("b-collapse"),Pe=(0,l.up)("b-modal"),Ve=(0,l.Q2)("b-toggle");return(0,l.wg)(),(0,l.iD)(l.HY,null,[(0,l._)("div",{class:(0,t.C_)(["main-lobby",[e.$root.theme?"light":"dark"]])},[(0,l._)("div",i,[r,(0,l._)("div",m,[(0,l.wy)((0,l._)("input",{class:"search_input",type:"text",placeholder:"방 제목 또는 #해쉬태그로 검색",style:{"font-size":"1.2em","font-family":"'Noto Sans KR', sans-serif"},"onUpdate:modelValue":o[0]||(o[0]=e=>n.keyword=e),onKeyup:o[1]||(o[1]=(0,s.D2)((e=>n.search(n.keyword)),["enter"]))},null,544),[[s.nr,n.keyword]]),(0,l._)("a",{href:"#",class:"search_icon",onClick:o[2]||(o[2]=(0,s.iM)((e=>n.search(n.keyword)),["prevent"]))},c)]),d,u,1==n.searchingFlag?((0,l.wg)(),(0,l.iD)("div",w,[(0,l.Wm)(L,{style:{width:"3rem",height:"3rem"},label:"Loading..."})])):(0,l.kq)("",!0),2==n.searchingFlag?((0,l.wg)(),(0,l.iD)("div",h," '"+(0,t.zw)(n.searchedKeyword)+"' 에 대한 검색 결과입니다 ",1)):(0,l.kq)("",!0),2==n.searchingFlag&&0==n.roomSearched.length?((0,l.wg)(),(0,l.iD)("div",g,p)):(0,l.kq)("",!0),v,0!=n.searchingFlag?((0,l.wg)(),(0,l.iD)("div",y,[(0,l._)("div",k,[((0,l.wg)(!0),(0,l.iD)(l.HY,null,(0,l.Ko)(n.shownRoomSearched,(e=>((0,l.wg)(),(0,l.iD)("div",{key:e.room.roomNum,class:"room col-12 col-sm-6 col-md-4 col-lg-2"},[(0,l.Wm)(pe,{room:e},null,8,["room"])])))),128))])])):(0,l.kq)("",!0),n.shownRoomSearched.length<n.roomSearched.length?((0,l.wg)(),(0,l.iD)("div",b,[(0,l.Wm)(ve,{class:"border-0",style:{background:"none"},onClick:o[3]||(o[3]=e=>n.addShownRooms(6,n.shownRoomSearched,n.roomSearched))},{default:(0,l.w5)((()=>[_])),_:1})])):(0,l.kq)("",!0),0!=n.searchingFlag?((0,l.wg)(),(0,l.iD)("div",R)):(0,l.kq)("",!0),0!=n.searchingFlag?((0,l.wg)(),(0,l.iD)("div",H)):(0,l.kq)("",!0),0!=n.searchingFlag?((0,l.wg)(),(0,l.iD)("div",x)):(0,l.kq)("",!0),0!=n.searchingFlag?((0,l.wg)(),(0,l.iD)("div",T)):(0,l.kq)("",!0),P,V,(0,l._)("div",N,[(0,l._)("div",D,[(0,l._)("div",W,[(0,l._)("div",{class:"room-template outer main-color",onClick:o[4]||(o[4]=(...e)=>n.openModal&&n.openModal(...e))},U)]),((0,l.wg)(!0),(0,l.iD)(l.HY,null,(0,l.Ko)(n.shownRoomHistory,(e=>((0,l.wg)(),(0,l.iD)("div",{key:e.room.roomNum,class:"room col-12 col-sm-6 col-md-4 col-lg-2"},[(0,l.Wm)(pe,{room:e},null,8,["room"])])))),128))])]),n.shownRoomHistory.length<n.roomHistory.length?((0,l.wg)(),(0,l.iD)("div",Z,[(0,l.Wm)(ve,{class:"border-0",style:{background:"none"},onClick:o[5]||(o[5]=e=>n.addShownRooms(6,n.shownRoomHistory,n.roomHistory))},{default:(0,l.w5)((()=>[q])),_:1})])):(0,l.kq)("",!0),C,0!=n.userHashs.length?((0,l.wg)(),(0,l.iD)("div",z,[(0,l._)("div",F,[((0,l.wg)(!0),(0,l.iD)(l.HY,null,(0,l.Ko)(n.shownRoomRecommend,(e=>((0,l.wg)(),(0,l.iD)("div",{key:e.room.roomNum,class:"room col-12 col-sm-6 col-md-4 col-lg-2"},[(0,l.Wm)(pe,{room:e},null,8,["room"])])))),128))])])):(0,l.kq)("",!0),0==n.userHashs.length?((0,l.wg)(),(0,l.iD)("div",j,M)):(0,l.kq)("",!0),0!=n.userHashs.length&&n.shownRoomRecommend.length<n.roomRecommend.length?((0,l.wg)(),(0,l.iD)("div",A,[(0,l.Wm)(ve,{class:"border-0",style:{background:"none"},onClick:o[6]||(o[6]=e=>n.addShownRooms(6,n.shownRoomRecommend,n.roomRecommend))},{default:(0,l.w5)((()=>[I])),_:1})])):(0,l.kq)("",!0)])],2),(0,l.Wm)(Pe,{modelValue:n.isShow,"onUpdate:modelValue":o[18]||(o[18]=e=>n.isShow=e),id:"modal-newroom",centered:"","ok-disabled":"","hide-header":"","hide-footer":"","no-close-on-backdrop":"",onClose:o[19]||(o[19]=e=>n.resetModal()),onHidden:o[20]||(o[20]=e=>n.resetModal())},{default:(0,l.w5)((()=>[(0,l._)("div",K,[(0,l.Wm)(xe,null,{default:(0,l.w5)((()=>[(0,l.Wm)(ye,{class:"mb-3",modelValue:n.newRoom.roomName,"onUpdate:modelValue":o[7]||(o[7]=e=>n.newRoom.roomName=e),type:"text",placeholder:"방 이름",required:"",state:n.validation.roomName},null,8,["modelValue","state"]),(0,l.Wm)(ke,{state:n.validation.roomName},{default:(0,l.w5)((()=>[B])),_:1},8,["state"]),(0,l.Wm)(be,{modelValue:n.newRoom.roomHasPw,"onUpdate:modelValue":o[8]||(o[8]=e=>n.newRoom.roomHasPw=e)},{default:(0,l.w5)((()=>[E])),_:1},8,["modelValue"]),n.newRoom.roomHasPw?((0,l.wg)(),(0,l.j4)(ye,{key:0,class:"mt-2",placeholder:"방 비밀번호",modelValue:n.newRoom.roomPw,"onUpdate:modelValue":o[9]||(o[9]=e=>n.newRoom.roomPw=e),disabled:!n.newRoom.roomHasPw,state:n.validation.roomPw},null,8,["modelValue","disabled","state"])):(0,l.kq)("",!0),n.newRoom.roomHasPw?((0,l.wg)(),(0,l.j4)(ke,{key:1,state:n.validation.roomPw},{default:(0,l.w5)((()=>[$])),_:1},8,["state"])):(0,l.kq)("",!0),(0,l.Wm)(_e,{class:"mt-3",modelValue:n.newRoom.roomDescription,"onUpdate:modelValue":o[10]||(o[10]=e=>n.newRoom.roomDescription=e),placeholder:"방에 대한 설명이에요",rows:"2","max-rows":"2","no-resize":"",maxlength:"60"},null,8,["modelValue"]),(0,l._)("div",Y,[(0,l._)("div",O,[(0,l.Wm)(Re,{"input-id":"roomTags",modelValue:n.newRoom.roomHashTags,"onUpdate:modelValue":o[11]||(o[11]=e=>n.newRoom.roomHashTags=e),separator:" ,;",state:n.validation.roomHashTags,placeholder:"방 태그",duplicateTagText:"중복된 태그가 있어요 ",tagVariant:"success","remove-on-delete":""},null,8,["modelValue","state"]),(0,l.Wm)(ke,{state:n.validation.roomHashTags},{default:(0,l.w5)((()=>[Q])),_:1},8,["state"])]),(0,l.Wm)(He,{arrow:""},{content:(0,l.w5)((()=>[J])),default:(0,l.w5)((()=>[G])),_:1})]),(0,l._)("div",X,[(0,l.Wm)(be,{modelValue:n.newRoom.roomSound,"onUpdate:modelValue":o[12]||(o[12]=e=>n.newRoom.roomSound=e)},{default:(0,l.w5)((()=>[ee])),_:1},8,["modelValue"]),(0,l.Wm)(be,{modelValue:n.newRoom.roomVideo,"onUpdate:modelValue":o[13]||(o[13]=e=>n.newRoom.roomVideo=e)},{default:(0,l.w5)((()=>[oe])),_:1},8,["modelValue"])]),(0,l.wy)(((0,l.wg)(),(0,l.j4)(ve,{class:"my-1 mt-3"},{default:(0,l.w5)((()=>[ae])),_:1})),[[Ve,void 0,void 0,{collapse:!0}]]),(0,l.Wm)(Te,{id:"collapse",class:"mt-2"},{default:(0,l.w5)((()=>[(0,l.Wm)(xe,null,{default:(0,l.w5)((()=>[(0,l._)("label",le,"방 최대 인원 : "+(0,t.zw)(n.newRoom.roomCapacity)+" 명",1),(0,l.Wm)(ye,{id:"range-1",modelValue:n.newRoom.roomCapacity,"onUpdate:modelValue":o[14]||(o[14]=e=>n.newRoom.roomCapacity=e),type:"range",min:"1",max:"15"},null,8,["modelValue"]),(0,l._)("div",te,[(0,l.Wm)(be,{modelValue:n.newRoom.useAlarm,"onUpdate:modelValue":o[15]||(o[15]=e=>n.newRoom.useAlarm=e)},{default:(0,l.w5)((()=>[se])),_:1},8,["modelValue"]),(0,l.Wm)(He,{arrow:""},{content:(0,l.w5)((()=>[re])),default:(0,l.w5)((()=>[ie])),_:1})]),n.newRoom.useAlarm?((0,l.wg)(),(0,l.iD)("div",me,[(0,l._)("label",ne,"공부 시간 : "+(0,t.zw)(n.newRoom.roomStudyTime)+" 분",1),(0,l.Wm)(ye,{id:"range-1",modelValue:n.newRoom.roomStudyTime,"onUpdate:modelValue":o[16]||(o[16]=e=>n.newRoom.roomStudyTime=e),type:"range",min:"10",max:"300",step:"5"},null,8,["modelValue"]),(0,l._)("label",ce,"쉬는 시간 : "+(0,t.zw)(n.newRoom.roomRestTime)+" 분",1),(0,l.Wm)(ye,{id:"range-1",modelValue:n.newRoom.roomRestTime,"onUpdate:modelValue":o[17]||(o[17]=e=>n.newRoom.roomRestTime=e),type:"range",min:"5",max:"60",step:"5"},null,8,["modelValue"])])):(0,l.kq)("",!0),de,ue])),_:1})])),_:1})])),_:1}),(0,l._)("div",we,[(0,l.Wm)(ve,{variant:"success",disabled:!n.isAllValid(),onClick:n.makeRoom},{default:(0,l.w5)((()=>[he])),_:1},8,["disabled","onClick"]),ge,(0,l.Wm)(ve,{onClick:n.closeModal},{default:(0,l.w5)((()=>[fe])),_:1},8,["onClick"])])])])),_:1},8,["modelValue"])],64)}a(6699);var ve=a(2262),ye=a(6455),ke=a.n(ye),be=a(2895),_e=a(2745),Re=a(5173);const He={class:"room-template outer main-color"},xe=["href"],Te={class:"room-thumbnail flex-grow-1 flex-shrink-1"},Pe={class:"w-100 h-100",style:{position:"relative"}},Ve={style:{position:"absolute",right:"0.5em",bottom:"0.5em"}},Ne=(0,l._)("i",{class:"bi bi-mic-mute-fill"},null,-1),De=(0,l._)("i",{class:"bi bi-camera-video-off-fill"},null,-1),We=(0,l._)("i",{class:"bi bi-lock-fill"},null,-1),Se={class:"bi bi-people-fill me-1",style:{"font-style":"normal"}},Ue={key:1},Ze={class:"m-0"},qe=(0,l.Uk)(" Host "),Ce=["src"],ze={class:"d-flex flex-grow-0 flex-shrink-0 align-items-center",style:{"margin-top":"8px"}},Fe={class:"d-flex flex-column flex-grow-1",style:{"margin-left":"8px"}},je={style:{"font-size":"1.5em","font-weight":"bold"}},Le={class:"d-flex mt-1",style:{"min-height":"20px"}};function Me(e,o,a,s,i,r){const m=(0,l.up)("b-button"),n=(0,l.up)("b-badge"),c=(0,l.up)("b-avatar");return(0,l.wg)(),(0,l.iD)("div",He,[(0,l._)("a",{href:`/studyroom/${a.room.room.roomNum}`,class:"room-component d-flex flex-column"},[(0,l._)("div",Te,[(0,l._)("div",Pe,[(0,l._)("div",Ve,[a.room.room.roomSound?(0,l.kq)("",!0):((0,l.wg)(),(0,l.j4)(m,{key:0,pill:"",variant:"light",disabled:"",class:"rounded-circle p-0 px-1 me-2"},{default:(0,l.w5)((()=>[Ne])),_:1})),a.room.room.roomVideo?(0,l.kq)("",!0):((0,l.wg)(),(0,l.j4)(m,{key:1,pill:"",variant:"light",disabled:"",class:"rounded-circle p-0 px-1"},{default:(0,l.w5)((()=>[De])),_:1}))]),a.room.room.roomHasPw?((0,l.wg)(),(0,l.j4)(m,{key:0,style:{position:"absolute",top:"0.5em",left:"0.5em"},class:"rounded-circle p-0 px-1",variant:"light",disabled:""},{default:(0,l.w5)((()=>[We])),_:1})):(0,l.kq)("",!0),(0,l.Wm)(m,{pill:"",variant:"light",disabled:"",class:"p-0 pe-1 ps-2",style:{position:"absolute",right:"0.5em",top:"0.5em"}},{default:(0,l.w5)((()=>[(0,l._)("i",Se,"  "+(0,t.zw)(a.room.room.roomParticipationCount)+" / "+(0,t.zw)(a.room.room.roomCapacity),1)])),_:1}),a.room.room.userNum==s.userNum?((0,l.wg)(),(0,l.iD)("div",Ue,[(0,l._)("h5",Ze,[(0,l.Wm)(n,{pill:"",variant:"light",style:(0,t.j5)("position: absolute; opacity: 0.7; top: 0.2em;"+(a.room.room.roomHasPw?"left: 2.5em;":"left: 0.5em;"))},{default:(0,l.w5)((()=>[qe])),_:1},8,["style"])])])):(0,l.kq)("",!0),(0,l._)("img",{src:`${s.server_link}/showImg/thumbnail/${a.room.room.roomNum}`,style:{"max-width":"100%",height:"auto"}},null,8,Ce)])]),(0,l._)("div",ze,[(0,l.Wm)(c,{src:`${s.server_link}/showImg/profile/number/${a.room.room.userNum}`,size:"2em"},null,8,["src"]),(0,l._)("div",Fe,[(0,l._)("div",je,(0,t.zw)(a.room.room.roomName),1)])]),(0,l._)("div",Le,[((0,l.wg)(!0),(0,l.iD)(l.HY,null,(0,l.Ko)(a.room.roomHash,((e,o)=>((0,l.wg)(),(0,l.j4)(n,{key:o,pill:"",class:"mx-1",style:{background:"#cdeaf0 !important"}},{default:(0,l.w5)((()=>[(0,l.Uk)("#"+(0,t.zw)(e),1)])),_:2},1024)))),128))])],8,xe)])}var Ae=a(3907),Ie={props:{room:{type:Object,required:!0}},setup(){const e=(0,ve.iH)("https://i7b310.p.ssafy.io:8081/B310");return{server_link:e,userNum:(0,Ae.oR)().getters.getUserNum}}},Ke=a(3744);const Be=(0,Ke.Z)(Ie,[["render",Me]]);var Ee=Be,$e=a(2119),Ye={components:{MainRoomComp:Ee},setup(){const e=(0,Ae.oR)();let o=(0,ve.iH)([]),a=(0,ve.iH)([]),t=(0,ve.iH)(0),s=(0,ve.iH)(""),i=(0,ve.iH)("");async function r(e){if(e){if(t.value=1,a.value=[],e.includes("#")){e=e.slice(e.indexOf("#")),e=e.split("#");let l=[],t="";for(let o of e)o=o.replace(" ",""),o&&(l.push(o),t+="#"+o+", ");e=l,o.value=await be.Z.searchRoomWithTags(e),g(6,a.value,o.value),i.value=t.slice(0,-2)}else o.value=await be.Z.searchRoomWithName(e),g(6,a.value,o.value),i.value=e;t.value=2}}const m=(0,ve.iH)([]),n=(0,ve.iH)([]);let c=(0,ve.iH)([]),d=(0,ve.iH)([]);(0,l.wF)((()=>{h()}));let u=(0,ve.iH)([]),w=e.getters.getUserNum;async function h(){t.value=0,m.value=await be.Z.getRoomHistoryList(),u.value=await Re.Z.getHashTags(e.getters.getUserID),n.value=await be.Z.getRoomRecommendList(u.value);let o=[];for(let e=0;e<n.value.length;e++)w!=n.value[e].room.userNum&&o.push(n.value[e]);n.value=o,m.value.sort(f),n.value.sort(f),c.value=[],d.value=[],g(5,c.value,m.value),g(6,d.value,n.value)}function g(e,o,a){let l=o.length,t=a.length;for(let s=l+e;l<s&&l<t;l++)o.push(a[l])}function f(e,o){return e.room.userNum==w&&o.room.userNum!=w?-1:e.room.userNum!=w&&o.room.userNum==w?1:new Date(e.room.roomStartTime).getTime()>new Date(o.room.roomStartTime).getTime()?-1:new Date(e.room.roomStartTime).getTime()<new Date(o.room.roomStartTime).getTime()?1:0}const p=(0,ve.iH)(!1);function v(){p.value=!0}function y(){p.value=!1}function k(){R.roomName=null,R.roomDescription="",R.roomHasPw=!1,R.roomPw=null,R.roomHashTags=[],R.roomCapacity=15,R.roomSound=!1,R.roomVideo=!1,R.roomStudyTime=0,R.roomRestTime=0,R.useAlarm=!1}const b=(0,ve.qj)({roomName:(0,l.Fl)((()=>null==R.roomName?null:new RegExp(/^.{1,20}$/).test(R.roomName))),roomPw:(0,l.Fl)((()=>R.roomHasPw?null==R.roomPw?null:new RegExp(/^[a-zA-Z0-9]{4,10}$/).test(R.roomPw):null)),roomHashTags:(0,l.Fl)((()=>R.roomHashTags.length<=3&&null))});function _(){let e=b.roomName||!1,o=b.roomPw||!0,a=b.roomHashTags||!0;return e&&o&&a}const R=(0,ve.qj)({roomName:null,roomDescription:null,roomHasPw:!1,roomPw:null,roomCapacity:15,roomSound:!1,roomVideo:!1,roomStudyTime:0,roomRestTime:0,roomHashTags:[],useAlarm:!1}),H=(0,$e.tv)();async function x(){if(_())try{R.roomSound=!R.roomSound,R.roomVideo=!R.roomVideo;let e=await be.Z.creatRoom(R),o=document.getElementById("roomThumbnail");if(o.files.length>0){const a=new FormData;a.append("thumbnail",o.files[0]),await _e.Z.addThumbnail(e,a)}console.log("added tags",R.roomHashTags),await be.Z.addHashTags(e,R.roomHashTags),await(0,l.Y3)(),h(),y(),H.replace({path:"/studyroom/"+e})}catch(e){ke().fire({icon:"warning",title:e})}}return(0,l.bv)((()=>{const e=document.getElementById("roomThumbnail");e.addEventListener("change",(e=>{const o=e.target;if(o.files&&o.files[0]){const e=5242880;o.files[0].size>e&&(ke().fire({icon:"warning",title:"파일의 최대 크기는 5MB 입니다"}),o.value="");let a=o.files[0].name,l=a.lastIndexOf("."),t=a.substring(l+1,a.length).toLowerCase();"png"!==t&&"jpg"!==t&&(ke().fire({icon:"warning",title:"지원하지 않는 확장자입니다"}),o.value="")}}))})),{isShow:p,roomHistory:m,roomRecommend:n,shownRoomHistory:c,shownRoomRecommend:d,validation:b,newRoom:R,openModal:v,closeModal:y,resetModal:k,makeRoom:x,isAllValid:_,addShownRooms:g,userHashs:u,searchingFlag:t,roomSearched:o,shownRoomSearched:a,keyword:s,searchedKeyword:i,search:r}}};const Oe=(0,Ke.Z)(Ye,[["render",pe]]);var Qe=Oe}}]);
//# sourceMappingURL=465.8768bd62.js.map