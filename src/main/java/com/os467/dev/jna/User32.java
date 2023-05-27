package com.os467.dev.jna;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;


public interface User32 extends StdCallLibrary {

    User32 user32 = Native.load("user32",User32.class);

    WinDef.LRESULT SendMessageTimeoutA(WinDef.HWND hWnd, int msg, WinDef.WPARAM wParam, WinDef.LPARAM lParam, int fuFlags, int uTimeout, WinDef.DWORDByReference lpdwResult);

    WinDef.HWND	SetParent(WinDef.HWND hWndChild, WinDef.HWND hWndNewParent);

    WinDef.HWND FindWindowA(String lpClassName, String lpWindowName);

    WinDef.HWND FindWindowExA(WinDef.HWND hWndParent, WinDef.HWND hWndChildAfter, String lpszClass, String lpszWindow);

    WinDef.HWND GetClassNameA(WinDef.HWND hWnd, WinDef.HWND lpClassName, int nMaxCount);

    WinDef.HWND GetAncestor(WinDef.HWND hWnd);

    boolean EnuWindows(Callback c);

    boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, WinDef.HWND data);

    boolean MoveWindow(WinDef.HWND hWnd, int X, int Y, int nWidth, int nHeight, boolean bRepaint);

    boolean ShowWindow(WinDef.HWND hWnd, int nCmdShow);
}
