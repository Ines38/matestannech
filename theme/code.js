var postes=[];
var gouvernement=[]
 mo="mouhab";
 idpost=null;
var postelem;
//----------------------------loaded the page------------
 $(document).ready(async function(){
  
    $.get({url:"localhost:2020/user/read", success: async function(result){
        console.log("result")
        localStorage.setItem('userlist',userlist);
        console.log('123',JSON.parse(localStorage.getItem('userslist')))
    }

  })
   
    $.get({url: "http://localhost:2020/post", success: async function(result){
                insertIn = "";
                insertIn2="";
            
            //var gouvernement=[]
            console.log("result",JSON.stringify( result));
            localStorage.setItem('postes',JSON.stringify(result));
                await result.forEach(element=>{
                        insertIn2 +='<option value="'+element.postId+'" onclick="gopost('+element.postId+')"style=" min-width :100%;font-size:12px"> N :'+element.postName +' Ad : '+element.postAdress+' Go : '+element.gouvernement+ '</option>'; 
                        if(!gouvernement.includes(element.gouvernement)){
                            gouvernement.push(element.gouvernement)
                           }
                    }
                );
                console.log('second',(JSON.parse(localStorage.getItem("postes") )|| "[]"))
                await gouvernement.forEach(element =>{
                        insertIn +='<option value="'+element+'" onclick="gouv()">'+element+'</option>';
                    }
                );


                $("#gouv").html(insertIn);
                $("#post").html(insertIn2);
                $("#postAdmin").html(insertIn2);
                }
            }
        )
    }
 )


//
const fun = async () => {
    }
  
  


        
fun();


function gouv(){
    var postes1=JSON.parse(localStorage.getItem("postes") )|| "[]"
    var select = document.getElementById('gouv');
    var value = select.options[select.selectedIndex].value;

    console.log(value)
    insertIn='';
    postes1.forEach(element => {
            if(element.gouvernement==value){
                 insertIn +=  '<option value="'+element.postId+'" style=" min-width :100%" onclick="gopost('+element.postId+')">N :'+element.postName+' Ad :'+element.postAdress+'</option>';
                }
      $("#post").html(insertIn)
    });
}

function gopost(id){
    idpost=id;
    console.log(typeof postes.find(obj => {
        return obj.postId === idpost;
        
        
      }))
postes =localStorage.setItem("idpost", idpost);
console.log('hya l post ',postes)
localStorage.setItem('post',postes.find(obj => {
    return obj.postId === 1;
    
    
  }))
 window.location.href="post.html"
 
    
}

async function script2(){

    postes1 = JSON.parse(localStorage.getItem("postes") || "[]");
    var idpost1 = localStorage.getItem("idpost");

        postelem = postes1.find(obj => 
            {
                 return obj.postId == idpost1;
        })
    $("#gouvp").html(postelem.gouvernement);
    $("#adp").html(postelem.postAdress);
    $("#np").html(postelem.postName);
    $("#nb").html(postelem.currentNumber);
    
}


async function refre(){
	const response = await fetch("http://localhost:2020/post/"+localStorage.getItem("idpost")).then((response) => {
      return response.json();
    })
    .then((data) => {
		console.log(data.currentNumber);
		$("#nb").html(data.currentNumber);
	
	})
    
}


/* postelem = postes.find(obj => {
        return obj.postId === idpost;
        
      })*/

      //localStorage.setItem("idpost", idpost);
      //var idpost = localStorage.getItem("idpost");
        //localStorage.setItem("postes",JSON.stringify(postes));
       // console.log(postes)
       // users = JSON.parse(localStorage.getItem("postes") || "[]");
       // console.log( users)