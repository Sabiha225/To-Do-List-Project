let inputElement = document.querySelector('input');
let formElement = document.querySelector('form');
let listElement = document.querySelector('ul');
let totalTasksElement = document.querySelector('#total-tasks');

let taskList = [
    'Finish project',
    'Clean room' 
];

function createToDoList(id, task) {

    fetch(`http://localhost:8080/create`, {
      method: `post`,
      headers: { "Content-type": "application/json" },
      body: JSON.stringify({ id: id, task: task,}),
    })
      .then((response) => response.json())
      .then(data => console.log(`Request succeeded with JSON response ${data}`))
      .then(location.reload())
      .catch((error) => console.error(`Request failed: ${error}`));
  }



function deleteItem(e){
    let task = e.target.parentElement.previousElementSibling.innerHTML;
    let index = taskList.indexOf(task);
    if(index !== -1){
        taskList.splice(index, 1);
    }

    populateList();
}


function populateList(){
    listElement.innerHTML ='';
    taskList.forEach(function(item){
        let newItem = document.createElement('li');

        //Add new span for text
        let span = document.createElement('span');
        span.innerHTML = item;
        newItem.appendChild(span);

        //Add delete button
        let anchorElement = document.createElement('a');
        anchorElement.classList.add('delete');
        anchorElement.innerHTML = '<i class="fas fa-trash-alt"></i>';
        newItem.appendChild(anchorElement);

        anchorElement.addEventListener('click', (e)=> deleteItem(e));

        //Add Li to UL
        listElement.appendChild(newItem);

    });
    
    totalTasksElement.innerHTML = taskList.length;

}

populateList();

function doesNotHaveWhiteSpace(s){
    let stringWithoutSpace = s.trim();
    return stringWithoutSpace.length > 0;
}

function addTask(){
    if(inputElement.value && doesNotHaveWhiteSpace(inputElement.value) && !taskList.includes(inputElement.value)) {
        taskList.push(inputElement.value);
        populateList();
    }
    inputElement.value ='';
}

formElement.addEventListener('submit', function (e) {
    e.preventDefault();
    addTask();
})

