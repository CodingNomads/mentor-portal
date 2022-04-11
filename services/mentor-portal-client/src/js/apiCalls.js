let mentorId;
let mentor;
export let mentorList;
let studentId;
let student;
let studentList;

export function getMentors() {
    async () => {
        const response = await fetch("http://localhost:8080/api/mentors", {
        mode: 'cors',
        credentials: "same-origin"
    })
    return mentorList = await response.json()
    };
};

export async function getStudents() {
    const response = await fetch("http://localhost:8080/api/students", {
            mode: 'cors',
            credentials: "same-origin"
    })
    studentList = await response.json()
    console.log(studentList)
};

export async function getMentor(mentorId) {
    const url = `http://localhost:8080/api/mentors/${mentorId}`
        console.log(mentorId)
        console.log(url)
        const headers = {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        const response = await fetch(url, {
            headers,
            mode: 'cors',
            credentials: "same-origin"
    });
    mentor = await response.json();
};

export async function getStudent(studentId) {
    const url = `http://localhost:8080/api/students/${studentId}`
        console.log(studentId)
        console.log(url)
        const headers = {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        const response = await fetch(url, {
            headers,
            mode: 'cors',
            credentials: "same-origin"
    });
    student = await response.json();
};