// auth.js
// =======================
// Quản lý JWT token & API call chung
// =======================

const TOKEN_KEY = "jwtToken";

// Lưu token vào localStorage
function saveToken(token) {
    localStorage.setItem(TOKEN_KEY, token);
}

// Lấy token từ localStorage
function getToken() {
    return localStorage.getItem(TOKEN_KEY);
}

// Xóa token 
function clearToken() {
    localStorage.removeItem(TOKEN_KEY);
}

//Xoá token xong đăng xuất
function logout() {
    clearToken();
    window.location.href = "/login";
}


// Giải mã JWT (để lấy user info)
function parseJwt(token) {
    if (!token) return null;
    try {
        const base64Url = token.split(".")[1];
        const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
        const jsonPayload = decodeURIComponent(
            atob(base64)
                .split("")
                .map(c => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
                .join("")
        );
        return JSON.parse(jsonPayload);
    } catch (e) {
        console.error("Invalid JWT token", e);
        return null;
    }
}

// Hàm gọi API có sẵn token
async function authFetch(url, options = {}) {
    const token = getToken();
    if (!token) {
        window.location.href = "/login";
        return;
    }

    options.headers = {
        ...(options.headers || {}),
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };

    const response = await fetch(url, options);

    // Nếu token hết hạn
    if (response.status === 401) {
        clearToken();
        return;
    }

    return response;
}

// Check login khi load trang
function requireAuth() {
    const token = getToken();
    if (!token) {
        window.location.href = "/login";
        return;
    }

    // Có thể check hạn token ở đây
    const payload = parseJwt(token);
    if (payload && payload.exp * 1000 < Date.now()) {
        clearToken();
    }
}
