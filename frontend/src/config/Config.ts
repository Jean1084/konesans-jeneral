const prod = {
    BACKEND_BASE_URL: window.location.origin + '/api',
}
  
const local = {
    BACKEND_BASE_URL: 'http://localhost:8080/api',
}

export const config = process.env.NODE_ENV === 'development' ? local : prod