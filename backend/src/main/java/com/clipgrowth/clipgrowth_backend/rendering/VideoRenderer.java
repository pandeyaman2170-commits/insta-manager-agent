package com.clipgrowth.clipgrowth_backend.rendering;

import com.clipgrowth.clipgrowth_backend.clip.Clip;

public interface VideoRenderer {

    String render(Clip clip) throws Exception;
}