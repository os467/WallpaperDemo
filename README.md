# WallpaperEngine
Built from Java for windows desktop wallpaper engine project.


Currently only video playback using native ffmpeg is implemented, with the video window bound to a specified PM window subwindow via JNA.

Current problem: The ffplay playback process created via a ProcessBuilder external call does not run the video properly when the parent process is working.
Solution: Create a playback window via javacv.
