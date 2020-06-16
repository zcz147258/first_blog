//引入需要权限控制的组件路由页面
const Music = () =>import("@/components/item/music")
const Display = () =>import("@/components/item/display")
const Blog = () =>import("@/components/item/blog")
const Video = () =>import("@/components/item/video")
const Self = () =>import("@/components/item/self")
const Word = () =>import("@/components/item/word")
const PlayVideo = () =>import("@/components/playvideo")
export default {
    Music,
    Display,
    Blog,
    Video,
    Self,
    Word,
    PlayVideo
}